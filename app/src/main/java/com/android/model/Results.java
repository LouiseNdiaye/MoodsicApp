package com.android.model;

import java.io.Serializable;

public class Results implements Serializable {

    Integer points;
    String era;

    public Results(Integer points, String era) {
        this.points = points;
        this.era = era;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }
}