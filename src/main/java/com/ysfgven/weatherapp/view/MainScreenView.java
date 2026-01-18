    package com.ysfgven.weatherapp.view;

    import com.ysfgven.weatherapp.controller.FileController;
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
    import javafx.scene.Parent;


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
            mainScreenController = new MainScreenController(locationListView,new FileController(locationListView));
        }

        public void createMainScreenUI() {
            borderPane = new BorderPane();
            borderPane.setStyle("-fx-background-color: #f8f9fa;");

            weatherDetailView = new WeatherDetailView();
            locationListView = new LocationListView(this::onLocationSelected);
            weatherController = new WeatherController(weatherDetailView);

            HBox inputBox = createSearchBox();
            borderPane.setTop(inputBox);

            VBox leftContainer = new VBox(locationListView.getListView());
            VBox.setVgrow(locationListView.getListView(), Priority.ALWAYS);

            leftContainer.setPrefWidth(300);
            leftContainer.setMinWidth(300);
            leftContainer.setPadding(new Insets(20));
            leftContainer.setStyle(
                    "-fx-background-color: white; " +
                            "-fx-background-radius: 30; " +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.03), 15, 0, 5, 0);"
            );

            Parent detailContent = weatherDetailView.getDetailedView();
            HBox.setHgrow(detailContent, Priority.ALWAYS);

            HBox mainContentArea = new HBox(20);
            mainContentArea.setPadding(new Insets(20));
            mainContentArea.setAlignment(Pos.TOP_LEFT);

            leftContainer.maxHeightProperty().bind(((VBox)detailContent).heightProperty());

            mainContentArea.getChildren().addAll(leftContainer, detailContent);

            borderPane.setCenter(mainContentArea);

            Scene scene = new Scene(borderPane, 1200, 800);

            if (getClass().getResource("/style.css") != null) {
                scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            }
            IconManager.setApplicationIcon(stage);

            stage.setScene(scene);
            stage.show();
        }

        private HBox createSearchBox() {
            TextField cityInput = new TextField();
            cityInput.setPromptText("Search city...");
            cityInput.setPrefHeight(40);
            cityInput.setPrefWidth(300);
            cityInput.setStyle("-fx-background-radius: 20; -fx-padding: 0 15 0 15;");

            Button addButton = new Button("Add");
            addButton.setPrefHeight(40);
            addButton.setPrefWidth(80);
            addButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; " +
                    "-fx-background-radius: 20; -fx-font-weight: bold; -fx-cursor: hand;");

            addButton.setOnAction(e -> {
                if (!cityInput.getText().trim().isEmpty()) {
                    mainScreenController.addNewLocation(cityInput.getText().trim());
                    cityInput.clear();
                }
            });

            HBox box = new HBox(15, cityInput, addButton);
            box.setPadding(new Insets(30, 20, 10, 20));
            box.setAlignment(Pos.CENTER);
            return box;
        }

        private void onLocationSelected(Locations location) {
            if (location != null) {
                weatherController.onLocationSelected(location.getLocationName());
            }
        }
        public LocationListView getLocationListView() {
            return locationListView;
        }
    }