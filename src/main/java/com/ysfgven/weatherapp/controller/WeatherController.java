package com.ysfgven.weatherapp.controller;

import com.ysfgven.weatherapp.model.WeatherData;
import com.ysfgven.weatherapp.service.APIService;
import com.ysfgven.weatherapp.view.LocationListView;
import com.ysfgven.weatherapp.view.WeatherDetailView;
import javafx.concurrent.Task;


public class WeatherController {
    private APIService apiService;
    private WeatherDetailView detailView;
    private LocationListView locationListView;

    public WeatherController(WeatherDetailView detailView,LocationListView locationListView) {
        this.apiService = new APIService(System.getenv("WEATHER_API_KEY"));
        this.detailView = detailView;
        this.locationListView = locationListView;
        initSelectionListener();
    }

    public void onLocationSelected(String locationName) {
        Task<WeatherData> weatherTask = new Task<>() {
            @Override
            protected WeatherData call() throws Exception {
                return apiService.getWeatherDataOfLocation(locationName);
            }
        };

        weatherTask.setOnSucceeded(e -> {
            WeatherData data = weatherTask.getValue();
            if (data != null) {
                detailView.update(data);
            } else {

                locationListView.getListView().getSelectionModel().clearSelection();
            }
        });

        weatherTask.setOnFailed(e -> {
            locationListView.getListView().getSelectionModel().clearSelection();
            System.err.println("Weather data could not be fetched.");
        });

        new Thread(weatherTask).start();
    }
    private void initSelectionListener() {
        this.detailView.setOnDaySelected(selectedDay -> {
            detailView.displayForecastDay(selectedDay);
        });
    }

}
