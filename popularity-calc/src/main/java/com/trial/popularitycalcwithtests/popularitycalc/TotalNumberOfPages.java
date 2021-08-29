package com.trial.popularitycalcwithtests.popularitycalc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalNumberOfPages {

    private int total_pages;

    public TotalNumberOfPages() {}

    public TotalNumberOfPages(int total_pages) {
        this.total_pages = total_pages;
    }


    // getters and setters

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotalPages(int total_pages) {
        this.total_pages = total_pages;
    }

}
