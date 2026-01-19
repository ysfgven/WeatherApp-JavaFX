package com.ysfgven.weatherapp.service;

import java.util.List;

public class ParseHelper {
    private Location location;
    private Current current;
    private Forecast forecast;

    public Location getLocation() { return location; }

    public Current getCurrent() { return current; }

    public Forecast getForecast() { return forecast; }

    public static class Location {
        private String name;

        public String getName() { return name; }

    }

    public static class Current {
        private double temp_c;
        private double temp_f;
        private double feelslike_c;
        private double feelslike_f;
        private int humidity;
        private int cloud;
        private int is_day;
        private Condition condition;
        private double wind_mph;
        private double wind_kph;
        private int wind_degree;
        private String wind_dir;
        private String last_updated;

        public double getTemp_c() { return temp_c; }
        public double getTemp_f() { return temp_f; }
        public double getFeelslike_c() { return feelslike_c; }
        public double getFeelslike_f() { return feelslike_f; }
        public int getHumidity() { return humidity; }
        public int getCloud() { return cloud; }
        public int getIs_day() { return is_day; }
        public Condition getCondition() { return condition; }
        public double getWind_mph() { return wind_mph; }
        public double getWind_kph() { return wind_kph; }
        public int getWind_degree() { return wind_degree; }
        public String getWind_dir() { return wind_dir; }
        public String getLast_updated() { return last_updated; }
    }

    public static class Forecast {
        private List<ForecastDayHelper> forecastday;

        public List<ForecastDayHelper> getForecastday() { return forecastday; }
    }

    public static class ForecastDayHelper {
        private String date;
        private DayHelper day;

        public String getDate() { return date; }
        public DayHelper getDay() { return day; }
    }

    public static class DayHelper {
        private double maxtemp_c;
        private double avgtemp_c;
        private double mintemp_c;
        private double maxwind_kph;
        private int avghumidity;
        private int daily_chance_of_rain;
        private Condition condition;

        public double getMaxtemp_c() { return maxtemp_c; }
        public double getAvgtemp_c() { return avgtemp_c; }
        public double getMintemp_c() { return mintemp_c; }
        public double getMaxwind_kph() { return maxwind_kph; }
        public int getAvghumidity() { return avghumidity; }
        public int getDaily_chance_of_rain() { return daily_chance_of_rain; }
        public Condition getCondition() { return condition; }
    }

    public static class Condition {
        private String text;
        private String icon;

        public String getText() { return text; }
        public String getIcon() { return icon; }
    }
}