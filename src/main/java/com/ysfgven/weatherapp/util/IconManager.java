package com.ysfgven.weatherapp.util;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.InputStream;

public class IconManager {

    private static final String ICON_PATH = "/icons/icon.png";

    public static void setApplicationIcon(Stage stage) {
        try {
            InputStream iconStream = IconManager.class.getResourceAsStream(ICON_PATH);

            if (iconStream != null) {
                Image icon = new Image(iconStream);
                stage.getIcons().add(icon);
            } else {
                ErrorHandler.showError("Error","Icon file is missing : " + ICON_PATH);
            }
        } catch (Exception e) {
            ErrorHandler.logException(e);
        }
    }
}