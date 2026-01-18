package com.ysfgven.weatherapp.controller;
import com.ysfgven.weatherapp.model.Locations;
import com.ysfgven.weatherapp.service.IOService;

import com.ysfgven.weatherapp.view.LocationListView;

import java.io.File;

import static com.ysfgven.weatherapp.util.ErrorHandler.logException;
import static com.ysfgven.weatherapp.util.ErrorHandler.showError;

public class FileController {
    private final IOService ioService = new IOService();

    private final String userHome = System.getProperty("user.home");
    private final File dir = new File(userHome + "/weatherapp/locations");
    private final File csvFile = new File(dir, "locations.csv");

    private final LocationListController locationListController;

    public FileController(LocationListView listView) {
        this.locationListController = new LocationListController(listView);
    }

    public void fileCheck() {
        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!csvFile.exists()) {
            try {
                csvFile.createNewFile();
            } catch (Exception e) {
                showError("Error","Missing files could not be created");
                logException(e);
            }
        }
    }

    public void giveLocationListtoController() {
        locationListController.setLocationList(ioService.readCSV(csvFile));
    }
    public void saveLocation(Locations location) {
        ioService.appendLocation(location, csvFile);
    }

}