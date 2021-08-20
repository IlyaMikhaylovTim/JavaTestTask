package com.trial.popularitycalcwithtests.popularitycalc;

import java.util.List;

public class FilmInfo {

    private int id;
    private int vote_count;
    private boolean video;
    private double vote_average;
    private String title;
    private double popularity;
    private String original_language;
    private String original_title;
    private List<Integer> genre_ids;
    private boolean adult;
    private String overview;
    private List<Integer> release_date;

    public FilmInfo() {
    }

    public FilmInfo(int id, int vote_count, boolean video, double vote_average, String title, double popularity, String original_language, String original_title, List<Integer> genre_ids, boolean adult, String overview, List<Integer> release_date) {
        this.id = id;
        this.vote_count = vote_count;
        this.video = video;
        this.vote_average = vote_average;
        this.title = title;
        this.popularity = popularity;
        this.original_language = original_language;
        this.original_title = original_title;
        this.genre_ids = genre_ids;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "FilmInfo{" +
                "id=" + id +
                ", vote_count=" + vote_count +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", genre_ids=" + genre_ids +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", release_date=" + release_date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<Integer> getRelease_date() {
        return release_date;
    }

    public void setRelease_date(List<Integer> release_date) {
        this.release_date = release_date;
    }

}
