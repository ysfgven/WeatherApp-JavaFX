package com.ysfgven.weatherapp.service;

import com.google.gson.Gson;
import com.ysfgven.weatherapp.model.WeatherData;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static com.ysfgven.weatherapp.util.ErrorHandler.logException;
import static com.ysfgven.weatherapp.util.ErrorHandler.showError;

public class APIService {
    private final String apiKey;
    private final Gson gson = new Gson();
    private final HttpClient client = HttpClient.newHttpClient();


    public APIService(String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherData getWeatherDataOfLocation(String location){
        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + encodedLocation;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return getWeather(response.body());
        } catch (java.io.IOException e){
            showError("Error", e.getMessage()+"Check the logs for more information.");
            logException(e);
        }catch (InterruptedException e){
            showError("Error", e.getMessage()+"Check the logs for more information.");
            logException(e);

        }

        return null; //TODO:make it safe
    }


    public WeatherData getWeather(String json) {
        ParseHelper parseHelper = gson.fromJson(json, ParseHelper.class);

        WeatherData wd = new WeatherData();
        wd.setCity(parseHelper.location.name);
        wd.setTempC(parseHelper.current.temp_c);
        wd.setTempF(parseHelper.current.temp_f);
        wd.setConditionText(parseHelper.current.condition.text);
        wd.setIcon(parseHelper.current.condition.icon);
        wd.setFeelsLikeC(parseHelper.current.feelslike_c);
        wd.setFeelsLikeF(parseHelper.current.feelslike_f);
        wd.setWindMph(parseHelper.current.wind_mph);
        wd.setWindKph(parseHelper.current.wind_kph);
        wd.setWindDegree(parseHelper.current.wind_degree);
        wd.setWindDir(parseHelper.current.wind_dir);
        wd.setHumidity(parseHelper.current.humidity);
        wd.setCloud(parseHelper.current.cloud);
        wd.setDay(parseHelper.current.is_day == 1);
        wd.setLastUpdated(parseHelper.current.last_updated);
        String rawIconUrl = parseHelper.current.condition.icon;
        if (rawIconUrl != null) {
            String formattedUrl = rawIconUrl.startsWith("//") ? "https:" + rawIconUrl : rawIconUrl;
            wd.setIconUrl(formattedUrl);
        }

        return wd;
    }

}
