package com.trial.popularitycalcwithtests.popularitycalc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListOfGenres {

    private List<GenreInformation> genres;

    public ListOfGenres() {}

    public ListOfGenres(List<GenreInformation> lst) {
        this.genres = lst;
    }


    // getters and setters

    public List<GenreInformation> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreInformation> genres) {
        this.genres = genres;
    }

}
