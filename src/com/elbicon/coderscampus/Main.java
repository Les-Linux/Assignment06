package com.elbicon.coderscampus;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here
        FileServiceImp vehicle = new FileServiceImp();
        final File model3File = new File("./sources/model3.csv");
        final File modelSFile = new File("./sources/modelS.csv");
        final File modelXFile = new File("./sources/modelX.csv");

        ArrayList<Vehicle> model3 = vehicle.Import(model3File);
        ArrayList<Vehicle> modelS = vehicle.Import(modelSFile);
        ArrayList<Vehicle> modelX = vehicle.Import(modelXFile);

        // Model3
        ConsoleHeader(FileServiceImp.Model(model3File.toString()));
        Map<Integer, List<Vehicle>> groupedByYearModel3 = GroupingByYear(model3);
        Map<String, List<Vehicle>> groupedByYearMonthModel3 = GroupingByYearMonth(model3);

        Set<Map.Entry<Integer,List<Vehicle>>> entrySetYearModel3 = EntrySet(groupedByYearModel3);
        Set<Map.Entry<String,List<Vehicle>>> entrySetYearMonthModel3 = EntrySetYearMonth(groupedByYearMonthModel3);

        ConsoleOutputBody(entrySetYearModel3);
        ConsoleOutputFooter(entrySetYearMonthModel3,FileServiceImp.Model(model3File.toString()));

        // ModelS
        ConsoleHeader(FileServiceImp.Model(modelSFile.toString()));
        Map<Integer, List<Vehicle>> groupedByYearModelS = GroupingByYear(modelS);
        Map<String, List<Vehicle>> groupedByYearMonthModelS = GroupingByYearMonth(modelS);

        Set<Map.Entry<Integer,List<Vehicle>>> entrySetYearModelS = EntrySet(groupedByYearModelS);
        Set<Map.Entry<String,List<Vehicle>>> entrySetYearMonthModelS = EntrySetYearMonth(groupedByYearMonthModelS);

        ConsoleOutputBody(entrySetYearModelS);
        ConsoleOutputFooter(entrySetYearMonthModelS,FileServiceImp.Model(modelSFile.toString()));

        //ModelX
        ConsoleHeader(FileServiceImp.Model(modelXFile.toString()));
        Map<Integer, List<Vehicle>> groupedByYearModelX = GroupingByYear(modelX);
        Map<String, List<Vehicle>> groupedByYearMonthModelX = GroupingByYearMonth(modelX);

        Set<Map.Entry<Integer,List<Vehicle>>> entrySetYearModelX = EntrySet(groupedByYearModelX);
        Set<Map.Entry<String,List<Vehicle>>> entrySetYearMonthModelX = EntrySetYearMonth(groupedByYearMonthModelX);

        ConsoleOutputBody(entrySetYearModelX);
        ConsoleOutputFooter(entrySetYearMonthModelX,FileServiceImp.Model(modelXFile.toString()));

    }
    private static Set<Map.Entry<Integer,List<Vehicle>>> EntrySet(Map<Integer, List<Vehicle>> groupedByYear ){
        Set<Map.Entry<Integer,List<Vehicle>>> entrySet = groupedByYear.entrySet();
        return entrySet;
    }
    private static Set<Map.Entry<String,List<Vehicle>>> EntrySetYearMonth(Map<String, List<Vehicle>> groupedByYear ){
        Set<Map.Entry<String,List<Vehicle>>> entrySet = groupedByYear.entrySet();
        return entrySet;
    }
    private static Map<Integer, List<Vehicle>> GroupingByYear(ArrayList<Vehicle> vehicle){
        Map<Integer, List<Vehicle>> groupedByYear = vehicle.stream()
                                                           .collect(Collectors.groupingBy((Vehicle) -> Vehicle.getSalesYear().getYear()));
        return groupedByYear;
    }
    private static Map<String, List<Vehicle>> GroupingByYearMonth(ArrayList<Vehicle> vehicle){
        Map<String, List<Vehicle>> groupedByYearMonth = vehicle.stream()
                .collect(Collectors.groupingBy((Vehicle) -> Vehicle.getSalesYear().format(DateTimeFormatter.ofPattern("yyyy-MM"))));
        return groupedByYearMonth;
    }
    private static void ConsoleHeader(String header){
        System.out.println("\n" + header + " Yearly Sales Report");
        System.out.println("---------------------------");
    }
    private static void ConsoleOutputBody(Set<Map.Entry<Integer, List<Vehicle>>> entrySetModel) {
        Map<Integer,Integer> vehicleSales = new HashMap<>();

        final int[] yearlySales = new int[1];
        //vehicleSales.put(entry.getKey(),
        entrySetModel.stream()
                .forEach((entry) -> {
                    Integer totalUnits = entry.getValue().stream()
                                     .map(m -> m.getUnits())
                                     .collect(Collectors.summingInt(c -> c.intValue()));
                    vehicleSales.put(entry.getKey(),totalUnits);
                });

        for (Map.Entry<Integer, Integer> sale : vehicleSales.entrySet()) {
            System.out.println(sale.getKey() + " -> " + sale.getValue());
        }
    }
    private static void ConsoleOutputFooter(Set<Map.Entry<String, List<Vehicle>>> entrySetModel,String model){
        Map<String,Integer> vehicleSales2 = new HashMap<>();

        final int[] yearlySales = new int[1];
        //vehicleSales.put(entry.getKey(),
        entrySetModel.stream()
                .forEach((entry) -> {
                    Integer totalUnits = entry.getValue().stream()
                            .map(m -> m.getUnits())
                            .collect(Collectors.summingInt(c -> c.intValue()));
                    vehicleSales2.put(entry.getKey().toString(),totalUnits);
                });

        Set<Map.Entry<String,Integer>> salesPerformances = vehicleSales2.entrySet();

        Map<String,Map<String,Integer>> salesPerformanceUnits = SalesPerformance(salesPerformances);

        Map<String,Integer> minSalesUnits = salesPerformanceUnits.get("min");
        Map<String,Integer> maxSalesUnits = salesPerformanceUnits.get("max");

        for(Map.Entry<String,Integer> saleUnit : maxSalesUnits.entrySet()){
            System.out.println("\nThe best month for " + model.substring(0,1).toUpperCase() + model.substring(1) +  " was: " + saleUnit.getKey());
        }
        for(Map.Entry<String,Integer> saleUnit : minSalesUnits.entrySet()){
            System.out.println("The worst month for " + model.substring(0,1).toUpperCase() + model.substring(1) +  " was: " + saleUnit.getKey());
        }
    }

    private static Map<String,Map<String,Integer>> SalesPerformance(Set<Map.Entry<String, Integer>> salesPerformances) {
        Map<String,Map<String,Integer>> salesPerformance = new HashMap<>();
        Map<String,Integer> minUnitSales = new HashMap<>();
        Map<String,Integer> maxUnitSales = new HashMap<>();

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

        maxUnitSales.put(maxSalesYear.toString(),maxUnits);
        minUnitSales.put(minSalesYear.toString(),minUnits);

        salesPerformance.put("max",maxUnitSales);
        salesPerformance.put("min",minUnitSales);

        return salesPerformance;
    }

}
