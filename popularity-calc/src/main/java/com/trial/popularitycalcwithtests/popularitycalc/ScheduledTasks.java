package com.trial.popularitycalcwithtests.popularitycalc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ScheduledTasks {

    public ScheduledTasks() {
    }

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static HashMap<Integer, Double> genreToPopularity = new HashMap<Integer, Double>();
    private static HashMap<Integer, Integer> genreToNumberOfFilms = new HashMap<Integer, Integer>();
    private static ArrayList<Integer> genresList = new ArrayList<>();

    private static int page = 1;
    private static boolean calculationFinished = false;

    public static HashMap<Integer, Double> getGenreToPopularity() {
        return genreToPopularity;
    }

    public static void setGenreToPopularity(HashMap<Integer, Double> genreToPopularity) {
        ScheduledTasks.genreToPopularity = genreToPopularity;
    }

    public static HashMap<Integer, Integer> getGenreToNumberOfFilms() {
        return genreToNumberOfFilms;
    }

    public static void setGenreToNumberOfFilms(HashMap<Integer, Integer> genreToNumberOfFilms) {
        ScheduledTasks.genreToNumberOfFilms = genreToNumberOfFilms;
    }

    public static ArrayList<Integer> getGenresList() {
        return genresList;
    }

    public static void setGenresList(ArrayList<Integer> genresList) {
        ScheduledTasks.genresList = genresList;
    }

    public static boolean isCalculationFinished() {
        return calculationFinished;
    }

    public static void setCalculationFinished(boolean calculationFinished) {
        ScheduledTasks.calculationFinished = calculationFinished;
    }

    public static int getPage() {
        return page;
    }

    public static void setPage(int page) {
        ScheduledTasks.page = page;
    }

    private static String baseUrl = "https://easy.test-assignment-a.loyaltyplant.net";
    private static String genresInfoUrl = "/3/genre/movie/list";
    private static String filmsInfoUrl = "/3/discover/movie";
    private static String apiKeyUrl = "?api_key=72b56103e43843412a992a8d64bf96e9";

    @Autowired
    private Environment environment;

    static {
        Client client = ClientBuilder.newClient();
        String genresUrl = baseUrl + genresInfoUrl + apiKeyUrl;
        GenresResponseEntity response = client.target(genresUrl)
                .request()
                .get(GenresResponseEntity.class);
        for (GenreInfo genreInfo : response.getGenres()) {
            genresList.add(genreInfo.getId());
        }
    }

    @Scheduled(fixedRate = 100)
    public void calculateStatistics() {

        Client client = ClientBuilder.newClient();

        if (page == 1958) {
            calculationFinished = true;
            String port = environment.getProperty("local.server.port");
            client.target("http://localhost:" + port + "/stopScheduler")
                    .request()
                    .get();
            return;
        }

        String pageNumberUrl = String.format("&page=%s", page);
        String url = baseUrl + filmsInfoUrl + apiKeyUrl + pageNumberUrl;
        ResponseEntity responseEntity = client.target(url)
                .request()
                .get(ResponseEntity.class);
        log.info(responseEntity.toString());

        for (FilmInfo filmInfo : responseEntity.getResults()) {
            for (int genre_id : filmInfo.getGenre_ids()) {
                double currentPopularity = genreToPopularity.getOrDefault(genre_id, 0.);
                genreToPopularity.put(genre_id, currentPopularity + filmInfo.getVote_average());

                int currentNumberOfFilms = genreToNumberOfFilms.getOrDefault(genre_id, 0);
                genreToNumberOfFilms.put(genre_id, currentNumberOfFilms + 1);
            }
        }

        ++page;

    }
}