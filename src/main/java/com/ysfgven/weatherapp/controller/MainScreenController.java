package com.ysfgven.weatherapp.controller;

import com.ysfgven.weatherapp.model.Locations;
import com.ysfgven.weatherapp.view.LocationListView;

public class MainScreenController {
    private LocationListView listView;
    private FileController fileController;


    public MainScreenController(LocationListView listView, FileController fileController) {
        this.listView = listView;
        this.fileController = fileController;
    }
    public void addNewLocation(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) return;

        Locations newLoc = new Locations(cityName.trim());

        listView.addLocation(newLoc);

        fileController.saveLocation(newLoc);
    }
}
