package com.trial.popularitycalcwithtests.popularitycalc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOfFilms {

    private List<FilmInformation> results;

    public ListOfFilms() {}

    public ListOfFilms(List<FilmInformation> results) {
        this.results = results;
    }


    // getters and setters

    public List<FilmInformation> getResults() {
        return results;
    }

    public void setResults(List<FilmInformation> results) {
        this.results = results;
    }

}
