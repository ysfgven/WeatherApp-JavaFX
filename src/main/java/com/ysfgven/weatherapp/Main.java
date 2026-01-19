package com.ysfgven.weatherapp;

import com.ysfgven.weatherapp.service.FileManager;
import com.ysfgven.weatherapp.view.MainScreenView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainScreenView mainScreenView = new MainScreenView(primaryStage);

        FileManager fileManager = new FileManager(mainScreenView.getLocationListView());

        fileManager.initFiles();
        fileManager.giveLocationListtoController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}