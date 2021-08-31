package com.trial.popularitycalcwithtests.popularitycalc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class GenresPopularityCalculationService {

    private static final Logger logger = LoggerFactory.getLogger(GenresPopularityCalculationService.class);
    private final int MAX_NUMBER_OF_CONNECTION_TRIES = 10;

    private RestTemplate restTemplate;
    private String baseUrl = "https://easy.test-assignment-a.loyaltyplant.net";
    private String genresInfoUrl = "/3/genre/movie/list";
    private String filmsInfoUrl = "/3/discover/movie";
    private String apiKeyUrl = "?api_key=72b56103e43843412a992a8d64bf96e9";
    private String pageUrl = "&page=";


    @Autowired
    private CalculatedData calculatedData;

    public GenresPopularityCalculationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    @PostConstruct
    private void initService() {
        initGenresList();
        initTotalNumberOfPages();
    }

    private void initGenresList() {
        String genresUrl = baseUrl + genresInfoUrl + apiKeyUrl;
        ListOfGenres genresResponse = restTemplate.getForObject(genresUrl, ListOfGenres.class);

        for (GenreInformation genreInformation : genresResponse.getGenres()) {
            calculatedData.getGenresList().add(genreInformation.getId());
        }

    }

    private void initTotalNumberOfPages() {
        String filmsUrl = baseUrl + filmsInfoUrl + apiKeyUrl + pageUrl + 1;
        TotalNumberOfPages numberOfPagesResponse = restTemplate.getForObject(filmsUrl, TotalNumberOfPages.class);
        calculatedData.setTotalNumberOfPages(numberOfPagesResponse.getTotal_pages());

    }


    public int totalNumberOfPages() {
        return calculatedData.getTotalNumberOfPages();
    }

    public int numberOfProcessedPages() {
        return calculatedData.getNumberOfProcessedPages();
    }

    private void increaseNumberOfProcessedPages() {
        calculatedData.setNumberOfProcessedPages(calculatedData.getNumberOfProcessedPages() + 1);
    }

    public boolean isValidGenreId(int genreId) {
        return calculatedData.getGenresList().contains(genreId);
    }

    public boolean isCalculationFinished() {
        return numberOfProcessedPages() + 1 == totalNumberOfPages();
    }

    public double popularityOfGenre(int genre_id) {
        return calculatedData.getGenreToPopularity().get(genre_id) /
                calculatedData.getGenreToNumberOfFilms().get(genre_id);
    }

    public ArrayList<Integer> getGenresIds() {
        return calculatedData.getGenresList();
    }

    private ListOfFilms getFilmsInfo(String url) {
        ListOfFilms listOfFilms = null;
        int numberOfConnectionTries = 0;

        while (numberOfConnectionTries < MAX_NUMBER_OF_CONNECTION_TRIES) {
            try {
                listOfFilms = restTemplate.getForObject(url, ListOfFilms.class);
                break;
            } catch (Exception e) {
                ++numberOfConnectionTries;
            }
        }

        return listOfFilms;
    }

    private void updatePopularityAndNumberOfFilms(ListOfFilms listOfFilms) {
        for (FilmInformation filmInformation : listOfFilms.getResults()) {
            for (int genre_id : filmInformation.getGenre_ids()) {
                synchronized(this) {
                    double currentPopularity = calculatedData.getGenreToPopularity().getOrDefault(genre_id, 0.);
                    calculatedData.getGenreToPopularity().put(genre_id, currentPopularity + filmInformation.getVote_average());

                    int currentNumberOfFilms = calculatedData.getGenreToNumberOfFilms().getOrDefault(genre_id, 0);
                    calculatedData.getGenreToNumberOfFilms().put(genre_id, currentNumberOfFilms + 1);
                }
            }
        }
    }


    @Async
    public void getFilmsInfoFromPageAndUpdateData(int page) throws InterruptedException {

        String filmsUrl = baseUrl + filmsInfoUrl + apiKeyUrl + pageUrl + page;

        ListOfFilms listOfFilms = getFilmsInfo(filmsUrl);

        if (listOfFilms == null) {
            logger.info("Failed to get page " + page + " from the server");
            return;
        }

        synchronized (this) {
            increaseNumberOfProcessedPages();
            updatePopularityAndNumberOfFilms(listOfFilms);
        }

        logger.info("Prossed page " + page + " on server");

    }

}
