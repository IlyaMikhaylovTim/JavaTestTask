package com.trial.popularitycalcwithtests.popularitycalc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOfFilms {

    private int page;
    private List<FilmInformation> results;

    public ListOfFilms() {}

    public ListOfFilms(int page, List<FilmInformation> results) {
        this.page = page;
        this.results = results;
    }


    // getters and setters

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<FilmInformation> getResults() {
        return results;
    }

    public void setResults(List<FilmInformation> results) {
        this.results = results;
    }

}
