package com.ysfgven.weatherapp;

import com.ysfgven.weatherapp.controller.FileController;
import com.ysfgven.weatherapp.view.MainScreenView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainScreenView mainScreenView = new MainScreenView(primaryStage);

        FileController fileController = new FileController(mainScreenView.getLocationListView());

        fileController.fileCheck();
        fileController.giveLocationListtoController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}