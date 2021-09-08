package com.trial.popularitycalcwithtests.popularitycalc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilmInformation {

    private double vote_average;
    private List<Integer> genre_ids;

    public FilmInformation() {
        super();
    }

    public FilmInformation(double vote_average, List<Integer> genre_ids) {
        super();
        this.vote_average = vote_average;
        this.genre_ids = genre_ids;
    }


    // getters and setters

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

}
