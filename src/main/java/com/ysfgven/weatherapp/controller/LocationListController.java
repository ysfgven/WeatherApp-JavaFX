package com.ysfgven.weatherapp.controller;

import com.ysfgven.weatherapp.model.Locations;
import com.ysfgven.weatherapp.view.LocationListView;
import java.util.List;

public class LocationListController {
    private LocationListView listView;

    public LocationListController(LocationListView listView) {
        this.listView = listView;
    }

    public void setLocationList(List<Locations> locations) {
        if (listView != null) {
            listView.setLocations(locations);
        }
    }
}