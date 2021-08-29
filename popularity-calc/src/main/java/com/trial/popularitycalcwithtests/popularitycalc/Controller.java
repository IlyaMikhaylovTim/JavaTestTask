package com.trial.popularitycalcwithtests.popularitycalc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    private GenresPopularityCalculationService service;

    @PostConstruct
    public void startCalculation() throws InterruptedException {
        for (int i = 1; i < service.totalNumberOfPages(); ++i) {
            service.getFilmsInfoFromPageAndUpdateData(i);
        }
    }


    // auxiliary methods

    private boolean isValidGenreId(int genreId) {
        return service.getData().getGenresList().contains(genreId);
    }

    private boolean isCalculationFinished() {
        return service.numberOfProcessedPages() + 1 == service.totalNumberOfPages();
    }

    private double popularityOfGenre(int genre_id) {
        return service.getData().getGenreToPopularity().get(genre_id) /
                service.getData().getGenreToNumberOfFilms().get(genre_id);
    }


    // URI-handling methods

    @GetMapping(value="/showGenresIds")
    public String showGenresIds() {
        return "Genres ids: " + service.getData().getGenresList().toString();
    }

    @GetMapping(value="/showProcessedPages")
    public String showProcessedPages() {
        int totalNumberOfPages = service.totalNumberOfPages();
        int numberOfProcessedPages = service.numberOfProcessedPages();

        return numberOfProcessedPages + " of "
                + totalNumberOfPages + " pages were processed";
    }

    @GetMapping(value="/showGenrePopularity/{genreId}")
    public String showGenrePopularity(@PathVariable("genreId") Integer genreId) {
        if (!isValidGenreId(genreId)) return "No such genre id";
        if (!isCalculationFinished()) return "Still in progress, come back later...";

        double meanPopularity = popularityOfGenre(genreId);

        return String.format("Mean popularity: %.1f", meanPopularity);
    }

}