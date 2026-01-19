package com.ysfgven.weatherapp.controller;

import com.ysfgven.weatherapp.model.Locations;
import com.ysfgven.weatherapp.service.FileManager;
import com.ysfgven.weatherapp.view.LocationListView;

public class MainScreenController {
    private LocationListView listView;
    private FileManager fileManager;


    public MainScreenController(LocationListView listView, FileManager fileManager) {
        this.listView = listView;
        this.fileManager = fileManager;
    }
    public void addNewLocation(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) return;

        Locations newLoc = new Locations(cityName.trim());

        listView.addLocation(newLoc);

        fileManager.saveLocation(newLoc);
    }
}
