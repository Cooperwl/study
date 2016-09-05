package com.study.mongo.demo;

/**
 * Created by wangliang on 2016/9/5.
 */
public class Location {

    private String type;
    private Double[] coordinates;

    public Location(String type, Double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }
}
