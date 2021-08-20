package com.trial.popularitycalcwithtests.popularitycalc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    private static final String SCHEDULED_TASKS = "scheduledTasks";

    @Autowired
    private ScheduledAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private ScheduledTasks scheduledTasks;

    private boolean validGenreId(int genreId) {
        return ScheduledTasks.getGenresList().contains(genreId);
    }

    @GetMapping(value="/showGenresIds")
    public String showGenresIds() {
        return "Genres ids: " + ScheduledTasks.getGenresList().toString();
    }

    @GetMapping(value = "/stopScheduler")
    public String stopScheduler(){
        postProcessor.postProcessBeforeDestruction(scheduledTasks, SCHEDULED_TASKS);
        return "Scheduler stopped";
    }

    @GetMapping(value="/showStat/{genre_id}")
    public String showStat(@PathVariable("genre_id") Integer genre_id) {
        if (!validGenreId(genre_id)) return "No such genre id";
        if (!ScheduledTasks.isCalculationFinished()) return "Still in progress, come back later...";

        double meanPopularity = ScheduledTasks.getGenreToPopularity().get(genre_id) /
                ScheduledTasks.getGenreToNumberOfFilms().get(genre_id);
        return String.format("Mean popularity: %.1f", meanPopularity);
    }

    @GetMapping(value="/showCurrentStat")
    public String showCurrentStat() {
        return ScheduledTasks.getPage() + " of 1957 pages were processed";
    }

}