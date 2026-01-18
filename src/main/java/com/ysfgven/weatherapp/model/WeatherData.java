package com.ysfgven.weatherapp.model;

public class WeatherData {
    private String city;
    private double temp_c;
    private double temp_f;
    private String conditionText;
    private String icon;
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
    private String iconUrl;

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getTempC() { return temp_c; }
    public void setTempC(double temp_c) { this.temp_c = temp_c; }

    public double getTempF() { return temp_f; }
    public void setTempF(double temp_f) { this.temp_f = temp_f; }

    public String getConditionText() { return conditionText; }
    public void setConditionText(String conditionText) { this.conditionText = conditionText; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public double getFeelsLikeC() { return feelsLikeC; }
    public void setFeelsLikeC(double feelsLikeC) { this.feelsLikeC = feelsLikeC; }

    public double getFeelsLikeF() { return feelsLikeF; }
    public void setFeelsLikeF(double feelsLikeF) { this.feelsLikeF = feelsLikeF; }

    public double getWindMph() { return windMph; }
    public void setWindMph(double windMph) { this.windMph = windMph; }

    public double getWindKph() { return windKph; }
    public void setWindKph(double windKph) { this.windKph = windKph; }

    public int getWindDegree() { return windDegree; }
    public void setWindDegree(int windDegree) { this.windDegree = windDegree; }

    public String getWindDir() { return windDir; }
    public void setWindDir(String windDir) { this.windDir = windDir; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public boolean isDay() { return isDay; }
    public void setDay(boolean day) { isDay = day; }

    public String getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }

    public double getCloud() { return cloud; }
    public void setCloud(double cloud) { this.cloud = cloud; }
}
