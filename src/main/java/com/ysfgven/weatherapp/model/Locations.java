package com.ysfgven.weatherapp.model;

public class Locations {
    private String locationName;

    public Locations(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return this.locationName;
    }
}