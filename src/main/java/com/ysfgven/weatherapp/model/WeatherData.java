package com.ysfgven.weatherapp.model;

import java.util.ArrayList;
import java.util.List;

public class WeatherData {
    private String city;
    private double tempC;
    private double tempF;
    private String conditionText;
    private String iconUrl;
    private double feelsLikeC;
    private double feelsLikeF;
    private double windMph;
    private double windKph;
    private int windDegree;
    private String windDir;
    private int humidity;
    private boolean isDay;
    private String lastUpdated;
    private double cloud;

    private List<ForecastDay> forecastList = new ArrayList<>();


    public List<ForecastDay> getForecastList() { return forecastList; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getTempC() { return tempC; }
    public void setTempC(double tempC) { this.tempC = tempC; }

    public void setTempF(double tempF) { this.tempF = tempF; }

    public String getConditionText() { return conditionText; }
    public void setConditionText(String conditionText) { this.conditionText = conditionText; }

    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

    public double getFeelsLikeC() { return feelsLikeC; }
    public void setFeelsLikeC(double feelsLikeC) { this.feelsLikeC = feelsLikeC; }

    public void setFeelsLikeF(double feelsLikeF) { this.feelsLikeF = feelsLikeF; }

    public void setWindMph(double windMph) { this.windMph = windMph; }

    public double getWindKph() { return windKph; }
    public void setWindKph(double windKph) { this.windKph = windKph; }

    public void setWindDegree(int windDegree) { this.windDegree = windDegree; }

    public String getWindDir() { return windDir; }
    public void setWindDir(String windDir) { this.windDir = windDir; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public void setDay(boolean day) { isDay = day; }

    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }

    public void setCloud(double cloud) { this.cloud = cloud; }
}