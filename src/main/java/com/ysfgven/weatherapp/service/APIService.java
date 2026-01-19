package com.ysfgven.weatherapp.service;

import com.google.gson.Gson;
import com.ysfgven.weatherapp.model.ForecastDay;
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

    public WeatherData getWeatherDataOfLocation(String location) {
        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
        String url = "http://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + encodedLocation + "&days=3&aqi=no&alerts=no";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return getWeather(response.body());
        } catch (java.io.IOException | InterruptedException e) {
            showError("Error", e.getMessage() + " Check the logs for more information.");
            logException(e);
        }
        return null;
    }

    public WeatherData getWeather(String json) {
        ParseHelper parseHelper = gson.fromJson(json, ParseHelper.class);
        WeatherData wd = new WeatherData();

        wd.setCity(parseHelper.getLocation().getName());
        wd.setTempC(parseHelper.getCurrent().getTemp_c());
        wd.setTempF(parseHelper.getCurrent().getTemp_f());
        wd.setConditionText(parseHelper.getCurrent().getCondition().getText());

        wd.setFeelsLikeC(parseHelper.getCurrent().getFeelslike_c());
        wd.setFeelsLikeF(parseHelper.getCurrent().getFeelslike_f());
        wd.setWindMph(parseHelper.getCurrent().getWind_mph());
        wd.setWindKph(parseHelper.getCurrent().getWind_kph());
        wd.setWindDegree(parseHelper.getCurrent().getWind_degree());
        wd.setWindDir(parseHelper.getCurrent().getWind_dir());
        wd.setHumidity(parseHelper.getCurrent().getHumidity());
        wd.setCloud(parseHelper.getCurrent().getCloud());
        wd.setDay(parseHelper.getCurrent().getIs_day() == 1);
        wd.setLastUpdated(parseHelper.getCurrent().getLast_updated());

        String currentIconUrl = parseHelper.getCurrent().getCondition().getIcon();
        if (currentIconUrl != null) {
            String formattedUrl = currentIconUrl.startsWith("//") ? "https:" + currentIconUrl : currentIconUrl;
            wd.setIconUrl(formattedUrl);
        }
        if (parseHelper.getForecast() != null) {
            for (int i = 0; i < parseHelper.getForecast().getForecastday().size(); i++) {

                ParseHelper.ForecastDayHelper helperItem = parseHelper.getForecast().getForecastday().get(i);
                ForecastDay fd = new ForecastDay();
                fd.setAvgTempC(helperItem.getDay().getAvgtemp_c());
                fd.setDate(helperItem.getDate());
                fd.setAvgHumidity(helperItem.getDay().getAvghumidity());
                fd.setMaxTempC(helperItem.getDay().getMaxtemp_c());
                fd.setMinTempC(helperItem.getDay().getMintemp_c());
                fd.setChanceOfRain(helperItem.getDay().getDaily_chance_of_rain());
                fd.setMaxWindKph(helperItem.getDay().getMaxwind_kph());
                fd.setConditionText(helperItem.getDay().getCondition().getText());

                String forecastIconUrl = helperItem.getDay().getCondition().getIcon();
                if (forecastIconUrl != null) {
                    String formattedUrl = forecastIconUrl.startsWith("//") ? "https:" + forecastIconUrl : forecastIconUrl;
                    fd.setIconUrl(formattedUrl);
                }

                wd.getForecastList().add(fd);
            }
        }

        return wd;
    }
}