package com.study.mongo.demo;

import java.io.Serializable;

/**
 * Created by wangliang on 2016/9/5.
 */
public class Location implements Serializable {

    private static final long serialVersionUID = -1317016731353355989L;
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
