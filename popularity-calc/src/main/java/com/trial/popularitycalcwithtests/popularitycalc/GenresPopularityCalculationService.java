package com.trial.popularitycalcwithtests.popularitycalc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class GenresPopularityCalculationService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(GenresPopularityCalculationService.class);

    private final int MAX_NUMBER_OF_CONNECTION_TRIES = 10;

    private final String baseUrl = "https://easy.test-assignment-a.loyaltyplant.net";
    private final String genresInfoUrl = "/3/genre/movie/list";
    private final String filmsInfoUrl = "/3/discover/movie";
    private final String apiKeyUrl = "?api_key=72b56103e43843412a992a8d64bf96e9";
    private final String pageUrl = "&page=";


    @Autowired
    private Data calculatedData;

    public GenresPopularityCalculationService(RestTemplateBuilder restTemplateBuilder) {
        super(restTemplateBuilder);
    }


    @PostConstruct
    @Override
    protected void initService() {
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
//        TotalNumberOfPages numberOfPagesResponse = restTemplate.getForObject(filmsUrl, TotalNumberOfPages.class);
//        calculatedData.setTotalNumberOfPages(numberOfPagesResponse.getTotal_pages());
        calculatedData.setTotalNumberOfPages(20);

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

    public double popularityOfGenre(int genreId) {
        return calculatedData.getGenreToPopularity().get(genreId) /
                calculatedData.getGenreToNumberOfFilms().get(genreId);
    }

    public ArrayList<Integer> genresIds() {
        return calculatedData.getGenresList();
    }


    private ListOfFilms retrieveFilmsInfo(String url) {
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
            for (int genreId : filmInformation.getGenre_ids()) {
                double currentPopularity = calculatedData.getGenreToPopularity().getOrDefault(genreId, 0.);
                calculatedData.getGenreToPopularity().put(genreId, currentPopularity + filmInformation.getVote_average());

                int currentNumberOfFilms = calculatedData.getGenreToNumberOfFilms().getOrDefault(genreId, 0);
                calculatedData.getGenreToNumberOfFilms().put(genreId, currentNumberOfFilms + 1);
            }
        }
    }


    @Async
    public void getFilmsInfoAndUpdateData(int page) throws InterruptedException {

        String filmsUrl = baseUrl + filmsInfoUrl + apiKeyUrl + pageUrl + page;

        ListOfFilms listOfFilms = retrieveFilmsInfo(filmsUrl);

        if (listOfFilms == null) {
            logger.info("Failed to get page " + page + " from the server");
            return;
        }

        synchronized (this) {
            updatePopularityAndNumberOfFilms(listOfFilms);
            increaseNumberOfProcessedPages();
        }

        logger.info("Processed page " + page + " on server");

    }

}
