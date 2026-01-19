    package com.ysfgven.weatherapp.view;

    import com.ysfgven.weatherapp.service.FileManager;
    import com.ysfgven.weatherapp.controller.MainScreenController;
    import com.ysfgven.weatherapp.controller.WeatherController;
    import com.ysfgven.weatherapp.model.Locations;
    import com.ysfgven.weatherapp.util.IconManager;
    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.TextField;
    import javafx.scene.layout.*;
    import javafx.stage.Stage;


    public class MainScreenView {

        private final Stage stage;
        private BorderPane borderPane;
        private LocationListView locationListView;
        private WeatherDetailView weatherDetailView;
        private WeatherController weatherController;
        private MainScreenController mainScreenController;

        public MainScreenView(Stage stage) {
            this.stage = stage;
            createMainScreenUI();
            mainScreenController = new MainScreenController(locationListView,new FileManager(locationListView));
        }
        //UI Creator Func
        public void createMainScreenUI() {
            borderPane = new BorderPane();

            weatherDetailView = new WeatherDetailView();
            locationListView = new LocationListView(this::onLocationSelected);
            weatherController = new WeatherController(weatherDetailView);

            borderPane.setTop(createSearchBox());

            // Left
            VBox leftContainer = new VBox(locationListView.getListView());
            locationListView.getListView().setMaxWidth(Double.MAX_VALUE);
            locationListView.getListView().setPrefHeight(650);

            leftContainer.setPrefWidth(300);
            leftContainer.getStyleClass().add("sidebar-container");

            //Right
            VBox detailContent = (VBox) weatherDetailView.getDetailedView();
            HBox.setHgrow(detailContent, Priority.ALWAYS);

            // Size Adj
            double fixedHeight = 650;
            leftContainer.setMinWidth(300);
            leftContainer.setPrefWidth(300);
            leftContainer.setMaxWidth(300);
            leftContainer.setPrefHeight(fixedHeight);
            leftContainer.setMaxHeight(fixedHeight);
            detailContent.setPrefHeight(fixedHeight);
            detailContent.setMaxHeight(fixedHeight);

            HBox mainContentArea = new HBox(20, leftContainer, detailContent);
            mainContentArea.setPadding(new Insets(20, 20, 60, 20));
            mainContentArea.setAlignment(Pos.TOP_LEFT);

            borderPane.setCenter(mainContentArea);

            Scene scene = new Scene(borderPane, 1200, 800);
            if (getClass().getResource("/style.css") != null) {
                scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            }
            IconManager.setApplicationIcon(stage);
            stage.setScene(scene);
            stage.show();
        }
        //SearchBox Creator Func
        private HBox createSearchBox() {
            //Field
            TextField cityInput = new TextField();
            cityInput.setPromptText("Search city...");
            cityInput.setPrefHeight(40);
            cityInput.setPrefWidth(300);
            cityInput.getStyleClass().add("search-field");

            //Add Button
            Button addButton = new Button("Add");
            cityInput.setOnAction(e -> addButton.fire());
            addButton.setPrefHeight(40);
            addButton.setPrefWidth(80);
            addButton.getStyleClass().add("add-button");
            addButton.setOnAction(e -> {
                if (!cityInput.getText().trim().isEmpty()) {
                    mainScreenController.addNewLocation(cityInput.getText().trim());
                    cityInput.clear();
                }
            });

            HBox box = new HBox(15, cityInput, addButton);
            box.getStyleClass().add("search-container");
            box.setAlignment(Pos.CENTER);
            return box;
        }
        //Checker Func
        private void onLocationSelected(Locations location) {
            if (location != null) {
                weatherController.onLocationSelected(location.getLocationName());
            }
        }
        //Getter
        public LocationListView getLocationListView() {
            return locationListView;
        }
    }