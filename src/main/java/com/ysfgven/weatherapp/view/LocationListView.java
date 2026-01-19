package com.ysfgven.weatherapp.view;

import com.ysfgven.weatherapp.model.Locations;
import javafx.scene.control.ListView;

import java.util.List;
import java.util.function.Consumer;

public class LocationListView {

    private ListView<Locations> listView;
    private Consumer<Locations> onSelect;

    public LocationListView(Consumer<Locations> onSelect) {
        this.onSelect = onSelect;
        createUI();
    }

    private void createUI() {
        listView = new ListView<>();

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
            if (selected != null) {
                onSelect.accept(selected);
            }
        });
    }
    /*

     ----------------For  efficiency I used runLater----------------
    */
    public void setLocations(List<Locations> locations) {
        javafx.application.Platform.runLater(() -> {
            listView.getItems().setAll(locations);
        });
    }
    public void addLocation(Locations location) {
        javafx.application.Platform.runLater(() -> {
            listView.getItems().add(location);
        });
    }


    //Getter
    public ListView<Locations> getListView() {
        return listView;
    }

}