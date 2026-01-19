package com.ysfgven.weatherapp.model;

public class ForecastDay extends WeatherData {
    private String date;
    private double maxTempC;
    private double avgTempC;
    private double minTempC;
    private double maxWindKph;
    private int avgHumidity;
    private int chanceOfRain;


    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public double getMaxTempC() { return maxTempC; }
    public void setMaxTempC(double maxTempC) { this.maxTempC = maxTempC; }

    public double getAvgTempC() { return avgTempC; }
    public void setAvgTempC(double avgTempC) { this.avgTempC = avgTempC; }

    public double getMinTempC() { return minTempC; }
    public void setMinTempC(double minTempC) { this.minTempC = minTempC; }

    public double getMaxWindKph() { return maxWindKph; }
    public void setMaxWindKph(double maxWindKph) { this.maxWindKph = maxWindKph; }

    public int getAvgHumidity() { return avgHumidity; }
    public void setAvgHumidity(int avgHumidity) { this.avgHumidity = avgHumidity; }

    public int getChanceOfRain() { return chanceOfRain; }
    public void setChanceOfRain(int chanceOfRain) { this.chanceOfRain = chanceOfRain; }

}