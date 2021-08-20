package com.trial.popularitycalcwithtests.popularitycalc;

import java.util.List;

import com.trial.popularitycalcwithtests.popularitycalc.GenreInfo;

public class GenresResponseEntity {

    private List<GenreInfo> genres;

    public GenresResponseEntity() {
    }

    public GenresResponseEntity(List<GenreInfo> lst) {
        this.genres = lst;
    }

    @Override
    public String toString() {
        return "OutputEntity{" +
                "lst=" + genres +
                '}';
    }

    public List<GenreInfo> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreInfo> genres) {
        this.genres = genres;
    }

}
