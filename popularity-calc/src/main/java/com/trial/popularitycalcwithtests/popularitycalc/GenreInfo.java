package com.trial.popularitycalcwithtests.popularitycalc;

public class GenreInfo {

    private int id;
    private String name;

    public GenreInfo() {
    }

    public GenreInfo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenreInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

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


