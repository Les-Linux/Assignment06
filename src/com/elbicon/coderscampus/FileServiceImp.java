package com.elbicon.coderscampus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileServiceImp implements FileService {
    ArrayList<Vehicle> vehicles = null;

    @Override
    public ArrayList<Vehicle> Import(File vehicleList) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(vehicleList))) {
            Stream<String> lines = bufferedReader.lines().skip(1);
            vehicles = new ArrayList<>();
            lines.forEachOrdered(line -> {
                String[] vehicleInfo = line.split(",");
                Vehicle vehicle = null;
                if (vehicleInfo != null) {
                    vehicle = new Vehicle();
                    vehicle.setModel(Model(vehicleList.toString()));

                    DateTimeFormatter dateFormat = new DateTimeFormatterBuilder()
                            .appendPattern("MMM-yy")
                            .parseCaseInsensitive()
                            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1) //LocalDate requires a day! Default to the first for each line
                            .toFormatter().withLocale(Locale.ENGLISH);

                    vehicle.setSalesYear(LocalDate.parse(vehicleInfo[0], dateFormat));
                    vehicle.setUnits(Integer.parseInt(vehicleInfo[1]));
                }
                vehicles.add(vehicle);
            });
        } catch (Exception e) {
            System.out.println("Exception Caught - " + e.getMessage());
        }
        return vehicles;
    }

    static String Model(String model) {
        //a regex pattern to extract the model from the file name passed into the method
        final String regex = "^(.+\\/)*(.+)\\.(.+)$";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(model);

        matcher.find();
        return matcher.group(2);
    }
}
