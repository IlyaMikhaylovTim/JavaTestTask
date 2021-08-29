package com.trial.popularitycalcwithtests.popularitycalc;

import java.util.List;

public class GenresInformation {

    private List<GenreInformation> genres;

    public GenresInformation() {}

    public GenresInformation(List<GenreInformation> lst) {
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
