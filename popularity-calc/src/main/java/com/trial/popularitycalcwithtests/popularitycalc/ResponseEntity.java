package com.trial.popularitycalcwithtests.popularitycalc;

import java.util.List;

public class ResponseEntity {

    private int page;
    private int total_results;
    private int total_pages;
    private List<FilmInfo> results;

    public ResponseEntity() {
    }

    public ResponseEntity(int page, int total_results, int total_pages, List<FilmInfo> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "page=" + page +
                ", total_results=" + total_results +
                ", total_pages=" + total_pages +
                ", results=" + results +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<FilmInfo> getResults() {
        return results;
    }

    public void setResults(List<FilmInfo> results) {
        this.results = results;
    }

}
