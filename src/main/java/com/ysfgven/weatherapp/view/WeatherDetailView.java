package com.ysfgven.weatherapp.view;

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

public class WeatherDetailView {
    private VBox view;
    private Label cityLabel;
    private Label temperatureLabel;
    private Label humidityLabel;
    private Label feelsLikeLabel;
    private Label windLabel;
    private ImageView weatherIcon;

    public WeatherDetailView() {
        createUI();
    }

    private void createUI() {
        view = new VBox();
        view.setPadding(new Insets(60));
        view.setSpacing(30);
        view.setAlignment(Pos.TOP_LEFT);
        view.setStyle("-fx-background-color: white; " +
                "-fx-background-radius: 30; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 20, 0, 0, 10);");

        view.setMaxWidth(Double.MAX_VALUE);
        view.setMaxHeight(Double.MAX_VALUE);

        cityLabel = new Label("Select City");
        cityLabel.setStyle("-fx-font-size: 45px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        weatherIcon = new ImageView();
        weatherIcon.setFitWidth(100);
        weatherIcon.setFitHeight(100);
        weatherIcon.setPreserveRatio(true);

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER_LEFT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(cityLabel, spacer, weatherIcon);

        temperatureLabel = new Label("-");
        temperatureLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold; -fx-text-fill: #e67e22;");
        temperatureLabel.setWrapText(true);

        String detailStyle = "-fx-font-size: 24px; -fx-text-fill: #95a5a6;";
        feelsLikeLabel = new Label("-"); feelsLikeLabel.setStyle(detailStyle);
        humidityLabel = new Label("-"); humidityLabel.setStyle(detailStyle);
        windLabel = new Label("-"); windLabel.setStyle(detailStyle);

        view.getChildren().addAll(header, temperatureLabel, feelsLikeLabel, humidityLabel, windLabel);
    }

    public void update(WeatherData wd) {
        cityLabel.setText(wd.getCity());
        temperatureLabel.setText(wd.getTempC() + "°C, " + wd.getConditionText());
        feelsLikeLabel.setText("Feels like: " + wd.getFeelsLikeC() + "°C");
        humidityLabel.setText("Humidity: " + wd.getHumidity() + "%");
        windLabel.setText("Wind: " + wd.getWindKph() + " kph, " + wd.getWindDir());
        if (wd.getIconUrl() != null) {
            weatherIcon.setImage(new Image(wd.getIconUrl(), true));
        }

    }

    public Parent getDetailedView() {

        view.setMaxWidth(Double.MAX_VALUE);
        view.setMaxHeight(Double.MAX_VALUE);
        return view;
    }
}