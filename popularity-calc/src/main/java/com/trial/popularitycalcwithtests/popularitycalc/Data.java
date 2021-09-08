package com.trial.popularitycalcwithtests.popularitycalc;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Hashtable;

@Component
public class Data {

    private Hashtable<Integer, Double> genreToPopularity = new Hashtable<Integer, Double>();
    private Hashtable<Integer, Integer> genreToNumberOfFilms = new Hashtable<Integer, Integer>();
    private ArrayList<Integer> genresList = new ArrayList<Integer>();

    private int numberOfProcessedPages = 0;
    private int totalNumberOfPages;


    // getters and setters

    public Hashtable<Integer, Double> getGenreToPopularity() {
        return genreToPopularity;
    }

    public void setGenreToPopularity(Hashtable<Integer, Double> genreToPopularity) {
        this.genreToPopularity = genreToPopularity;
    }

    public Hashtable<Integer, Integer> getGenreToNumberOfFilms() {
        return genreToNumberOfFilms;
    }

    public void setGenreToNumberOfFilms(Hashtable<Integer, Integer> genreToNumberOfFilms) {
        this.genreToNumberOfFilms = genreToNumberOfFilms;
    }

    public ArrayList<Integer> getGenresList() {
        return genresList;
    }

    public void setGenresList(ArrayList<Integer> genresList) {
        this.genresList = genresList;
    }

    public int getNumberOfProcessedPages() {
        return numberOfProcessedPages;
    }

    public void setNumberOfProcessedPages(int numberOfProcessedPages) {
        this.numberOfProcessedPages = numberOfProcessedPages;
    }

    public int getTotalNumberOfPages() {
        return totalNumberOfPages;
    }

    public void setTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
    }
}
