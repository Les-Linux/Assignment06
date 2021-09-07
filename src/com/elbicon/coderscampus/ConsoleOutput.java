package com.elbicon.coderscampus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsoleOutput {
    public static void Header(String header) {
        System.out.println("\n" + header + " Yearly Sales Report");
        System.out.println("---------------------------");
    }

    public static void Body(Set<Map.Entry<Integer, List<Vehicle>>> entrySetModel) {
        Map<Integer, Integer> vehicleSales = new HashMap<>();

        final int[] yearlySales = new int[1];
        //vehicleSales.put(entry.getKey(),
        entrySetModel.stream()
                .forEach((entry) -> {
                    Integer totalUnits = entry.getValue().stream()
                            .map(m -> m.getUnits())
                            .collect(Collectors.summingInt(c -> c.intValue()));
                    vehicleSales.put(entry.getKey(), totalUnits);
                });

        for (Map.Entry<Integer, Integer> sale : vehicleSales.entrySet()) {
            System.out.println(sale.getKey() + " -> " + sale.getValue());
        }
    }

    public static void Footer(Set<Map.Entry<String, List<Vehicle>>> entrySetModel, String model) {
        Map<String, Integer> vehicleSales2 = new HashMap<>();
        SalesPerformance salesPerformance = new SalesPerformance();

        entrySetModel.stream()
                .forEach((entry) -> {
                    Integer totalUnits = entry.getValue().stream()
                            .map(m -> m.getUnits())
                            .collect(Collectors.summingInt(c -> c.intValue()));
                    vehicleSales2.put(entry.getKey().toString(), totalUnits);
                });

        Set<Map.Entry<String, Integer>> salesPerformances = vehicleSales2.entrySet();

        Map<String, Map<String, Integer>> salesPerformanceUnits = salesPerformance.GetPerformanceValues(salesPerformances);

        Map<String, Integer> minSalesUnits = salesPerformanceUnits.get("min");
        Map<String, Integer> maxSalesUnits = salesPerformanceUnits.get("max");

        for (Map.Entry<String, Integer> saleUnit : maxSalesUnits.entrySet()) {
            System.out.println("\nThe best month for " + model.substring(0, 1).toUpperCase() + model.substring(1) + " was: " + saleUnit.getKey());
        }
        for (Map.Entry<String, Integer> saleUnit : minSalesUnits.entrySet()) {
            System.out.println("The worst month for " + model.substring(0, 1).toUpperCase() + model.substring(1) + " was: " + saleUnit.getKey());
        }
    }
}
