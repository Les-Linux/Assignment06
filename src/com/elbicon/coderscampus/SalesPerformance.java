package com.elbicon.coderscampus;

import java.util.*;
import java.util.stream.Collectors;

public class SalesPerformance {
    public Map<String, Map<String, Integer>> GetPerformanceValues(Set<Map.Entry<String, Integer>> salesPerformances) {
        Map<String, Map<String, Integer>> salesPerformance = new HashMap<>();
        Map<String, Integer> minUnitSales = new HashMap<>();
        Map<String, Integer> maxUnitSales = new HashMap<>();

        try {
            //Get max value
            Integer maxUnits = salesPerformances.stream()
                    .mapToInt(s -> s.getValue())
                    .max().orElseThrow(NoSuchElementException::new);
            //get max value year
            List<String> maxSalesYear = salesPerformances.stream()
                    .filter(f -> f.getValue().intValue() == maxUnits)
                    .map(m -> m.getKey())
                    .collect(Collectors.toList());


            //get min value
            Integer minUnits = salesPerformances.stream()
                    .mapToInt(s -> s.getValue())
                    .min().orElseThrow(NoSuchElementException::new);

            //get min value year
            List<String> minSalesYear = salesPerformances.stream()
                    .filter(f -> f.getValue().intValue() == minUnits)
                    .map(m -> m.getKey())
                    .collect(Collectors.toList());

            maxUnitSales.put(maxSalesYear.toString(), maxUnits);
            minUnitSales.put(minSalesYear.toString(), minUnits);

            salesPerformance.put("max", maxUnitSales);
            salesPerformance.put("min", minUnitSales);
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
        return salesPerformance;
    }
}
