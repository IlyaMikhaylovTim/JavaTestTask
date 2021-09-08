package com.trial.popularitycalcwithtests.popularitycalc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private GenresPopularityCalculationService service;

    @PostConstruct
    public void startCalculation() throws InterruptedException {
        for (int page = 1; page < totalNumberOfPages(); ++page) {
            service.getFilmsInfoAndUpdateData(page);
        }
    }


    // auxiliary methods

    private boolean isValidGenreId(int genreId) {
        return service.isValidGenreId(genreId);
    }

    private boolean isCalculationFinished() {
        return service.isCalculationFinished();
    }

    private double popularityOfGenre(int genre_id) {
        return service.popularityOfGenre(genre_id);
    }

    private ArrayList<Integer> genresIds() {
        return service.genresIds();
    }

    private int totalNumberOfPages() {
        return service.totalNumberOfPages();
    }

    private int numberOfProcessedPages() {
        return service.numberOfProcessedPages();
    }


    // URI-handling methods

    @GetMapping(value="/showGenresIds")
    public String showGenresIds() {
        return "Genres ids: " + genresIds().toString();
    }

    @GetMapping(value="/showProcessedPages")
    public String showProcessedPages() {
        return numberOfProcessedPages() + " of "
                + totalNumberOfPages() + " pages were processed";
    }

    @GetMapping(value="/showGenrePopularity/{genreId}")
    public String showGenrePopularity(@PathVariable("genreId") Integer genreId) {
        if (!isValidGenreId(genreId)) return "No such genre id";
        if (!isCalculationFinished()) return "Still in progress, come back later...";

        double meanPopularity = popularityOfGenre(genreId);

        return String.format("Mean popularity: %.1f", meanPopularity);
    }

}