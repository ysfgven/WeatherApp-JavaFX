package com.ysfgven.weatherapp.service;

public class ParseHelper {
    Location location;
    Current current;

    class Location {
        String name;
        String region;
        String country;
        String localtime;
    }

    class Current {
        double temp_c;
        double temp_f;
        double feelslike_c;
        double feelslike_f;
        int humidity;
        int cloud;
        int is_day;
        Condition condition;
        double wind_mph;
        double wind_kph;
        int wind_degree;
        String wind_dir;
        String last_updated;
    }

    class Condition {
        String text;
        String icon;
        int code;
    }
}
