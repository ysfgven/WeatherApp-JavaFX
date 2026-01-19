package com.ysfgven.weatherapp.view;

import com.ysfgven.weatherapp.model.ForecastDay;
import com.ysfgven.weatherapp.model.WeatherData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.function.Consumer;

public class WeatherDetailView {
    private VBox view;
    private Label cityLabel;
    private Label temperatureLabel;
    private Label humidityLabel;
    private Label feelsLikeLabel;
    private Label windLabel;
    private ImageView weatherIcon;
    private HBox forecastCards = new HBox();
    private Consumer<ForecastDay> onDaySelected;
    private Label chanceOfRainLabel;

    public WeatherDetailView() {
        createUI();
    }

    private void createUI() {
        view = new VBox();
        view.getStyleClass().add("weather-detail-container");
        view.setSpacing(25);
        view.setAlignment(Pos.TOP_LEFT);
        view.setMaxWidth(Double.MAX_VALUE);
        view.setMinHeight(Region.USE_PREF_SIZE);

        cityLabel = new Label("Select City");
        cityLabel.getStyleClass().add("city-label");

        weatherIcon = new ImageView();
        weatherIcon.setFitWidth(100);
        weatherIcon.setFitHeight(100);
        weatherIcon.setPreserveRatio(true);


        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);
        Region headerSpacer = new Region();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);
        header.getChildren().addAll(cityLabel, headerSpacer, weatherIcon);

        temperatureLabel = new Label("-");
        temperatureLabel.getStyleClass().add("main-temp-label");

        chanceOfRainLabel = new Label("Select a day for rain chance");
        chanceOfRainLabel.getStyleClass().add("info-label");

        feelsLikeLabel = new Label("-");
        feelsLikeLabel.getStyleClass().add("info-label");

        humidityLabel = new Label("-");
        humidityLabel.getStyleClass().add("info-label");

        windLabel = new Label("-");
        windLabel.getStyleClass().add("info-label");

        view.getChildren().addAll(header, temperatureLabel, feelsLikeLabel, humidityLabel, windLabel, chanceOfRainLabel);

        createForecastCards();

        view.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void createForecastCards(){
        forecastCards.setSpacing(15);
        forecastCards.setAlignment(Pos.CENTER);
        forecastCards.setPadding(new Insets(10));

        view.getChildren().add(forecastCards);
    }

    public void update(WeatherData wd) {
        //--------To update screen--------

        cityLabel.setText(wd.getCity());
        temperatureLabel.setText(wd.getTempC() + "°C, " + wd.getConditionText());
        feelsLikeLabel.setText("Feels like: " + wd.getFeelsLikeC() + "°C");
        humidityLabel.setText("Humidity: " + wd.getHumidity() + "%");
        windLabel.setText("Wind: " + wd.getWindKph() + " kph, " + wd.getWindDir());
        chanceOfRainLabel.setText("Select a day for rain chance");

        if (wd.getIconUrl() != null) {
            weatherIcon.setImage(new Image(wd.getIconUrl(), true));
        }

        forecastCards.getChildren().clear();
        for (var day : wd.getForecastList()) {
            forecastCards.getChildren().add(createSingleForecastCard(day));
        }
    }

    private VBox createSingleForecastCard(ForecastDay day) {
        VBox card = new VBox();
        card.setAlignment(Pos.CENTER);
        card.setSpacing(8);

        card.getStyleClass().add("forecast-card");

        // Selection
        card.setOnMouseClicked(event -> {
            // Remove selection effect
            forecastCards.getChildren().forEach(node ->
                    node.getStyleClass().remove("forecast-card-selected"));

            // Highlight just THAT card
            card.getStyleClass().add("forecast-card-selected");

            if (onDaySelected != null) {
                onDaySelected.accept(day);
            }
        });

        // ---  Filling  ---
        //Date and Icon
        LocalDate date = LocalDate.parse(day.getDate());
        String finalDate = date.equals(LocalDate.now()) ? "Today" : date.equals(LocalDate.now().plusDays(1)) ? "Tomorrow" :
                date.getDayOfWeek().getDisplayName(TextStyle.FULL, java.util.Locale.ENGLISH);

        ImageView icon = new ImageView();
        if (day.getIconUrl() != null) {
            icon.setImage(new Image(day.getIconUrl(), true));
        }
        icon.setFitWidth(35);
        icon.setFitHeight(35);

        //Labels
        Label dateLabel = new Label(finalDate);
        dateLabel.getStyleClass().add("forecast-date-label");

        Label maxTemp = new Label("Max: " + day.getMaxTempC() + "°C");
        maxTemp.getStyleClass().add("forecast-max-temp");

        Label minTemp = new Label("Min: " + day.getMinTempC() + "°C");
        minTemp.getStyleClass().add("forecast-min-temp");

        //Adding all to card to show
        card.getChildren().addAll(dateLabel, icon, maxTemp, minTemp);
        return card;
    }

    public void displayForecastDay(ForecastDay day) {
        //Labels
        temperatureLabel.setText(day.getAvgTempC() + "°C (Forecast,Average)");
        humidityLabel.setText("Avg Humidity: " + day.getAvgHumidity() + "%");
        windLabel.setText("Max Wind: " + day.getMaxWindKph() + " kph");
        chanceOfRainLabel.setText("Rain Chance: %" + day.getChanceOfRain());

        //Image
        if (day.getIconUrl() != null) {
            weatherIcon.setImage(new Image(day.getIconUrl(), true));
        }

    }
    //Checker Func
    public void setOnDaySelected(Consumer<com.ysfgven.weatherapp.model.ForecastDay> handler) {
        this.onDaySelected = handler;
    }

    //Getter
    public Parent getDetailedView() {
        return view;
    }
}