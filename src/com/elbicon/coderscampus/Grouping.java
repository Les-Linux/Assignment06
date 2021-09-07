package com.elbicon.coderscampus;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grouping {
    public Map<Integer, List<Vehicle>> ByYear(ArrayList<Vehicle> vehicle) {
        Map<Integer, List<Vehicle>> groupedByYear = vehicle.stream()
                .collect(Collectors.groupingBy((Vehicle) -> Vehicle.getSalesYear().getYear()));
        return groupedByYear;
    }

    public Map<String, List<Vehicle>> ByYearMonth(ArrayList<Vehicle> vehicle) {
        Map<String, List<Vehicle>> groupedByYearMonth = vehicle.stream()
                .collect(Collectors.groupingBy((Vehicle) -> Vehicle.getSalesYear().format(DateTimeFormatter.ofPattern("yyyy-MM"))));
        return groupedByYearMonth;
    }
}
