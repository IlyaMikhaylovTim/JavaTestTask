package com.trial.popularitycalcwithtests.popularitycalc;

public class GenreInformation {

    private int id;
    private String name;

    public GenreInformation() {}

    public GenreInformation(int id, String name) {
        this.id = id;
        this.name = name;
    }


    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


