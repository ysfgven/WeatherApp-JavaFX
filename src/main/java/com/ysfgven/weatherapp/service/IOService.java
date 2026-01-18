package com.ysfgven.weatherapp.service;

import com.ysfgven.weatherapp.model.Locations;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.ysfgven.weatherapp.util.ErrorHandler.logException;
import static com.ysfgven.weatherapp.util.ErrorHandler.showError;

public class IOService {

    public List<Locations> readCSV(File csvFile) {
        List<Locations> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                list.add(new Locations(line));
            }
        } catch (java.io.IOException e){
            showError("Error", e.getMessage()+"Can't read CSV File.Check the logs for more information.");
            logException(e);
        }
        return list;
    }

    public void appendLocation(Locations location, File csvFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            bw.write(location.getLocationName());
            bw.newLine();
        } catch (java.io.IOException e) {
            showError("Error", e.getMessage()+"Can't read CSV File.Check the logs for more information.");
            logException(e);
        }
    }
}