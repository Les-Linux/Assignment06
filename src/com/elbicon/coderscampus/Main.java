package com.elbicon.coderscampus;

import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here
        FileServiceImp vehicle = new FileServiceImp();
        final File model3File = new File("./sources/model3.csv");
        final File modelSFile = new File("./sources/modelS.csv");
        final File modelXFile = new File("./sources/modelS.csv");

        ArrayList<Vehicle> model3 = vehicle.Import(model3File);
        ArrayList<Vehicle> modelS = vehicle.Import(modelSFile);
        ArrayList<Vehicle> modelX = vehicle.Import(modelXFile);

        ConsoleHeader("Model3");
        Map<Integer, List<Vehicle>> groupedByYearModel3 = GroupingBy(model3);
        Set<Map.Entry<Integer,List<Vehicle>>> entrySetModel3 = EntrySet(groupedByYearModel3);
        ConsoleOutput(entrySetModel3);

        ConsoleHeader("ModelS");
        Map<Integer, List<Vehicle>> groupedByYearModelS = GroupingBy(modelS);
        Set<Map.Entry<Integer,List<Vehicle>>> entrySetModelS = EntrySet(groupedByYearModelS);
        ConsoleOutput(entrySetModelS);

        ConsoleHeader("ModelX");
        Map<Integer, List<Vehicle>> groupedByYearModelX = GroupingBy(modelX);
        Set<Map.Entry<Integer,List<Vehicle>>> entrySetModelX = EntrySet(groupedByYearModelX);
        ConsoleOutput(entrySetModelX);

    }
    private static Set<Map.Entry<Integer,List<Vehicle>>> EntrySet(Map<Integer, List<Vehicle>> groupedByYear ){
        Set<Map.Entry<Integer,List<Vehicle>>> entrySet = groupedByYear.entrySet();
        return entrySet;
    }
    private static Map<Integer, List<Vehicle>> GroupingBy(ArrayList<Vehicle> vehicle){
        Map<Integer, List<Vehicle>> groupedByYear = vehicle.stream()
                .collect(Collectors.groupingBy((Vehicle) -> Vehicle.getSalesYear().getYear()));
        return groupedByYear;
    }
    private static void ConsoleHeader(String header){
        System.out.println("\n" + header + " Yearly Sales Report");
        System.out.println("---------------------------");
    }
    private static void ConsoleOutput(Set<Map.Entry<Integer, List<Vehicle>>> entrySetModel) {
        entrySetModel.stream()
                .forEach((entry) -> {
                    System.out.println(entry.getKey() + " -> " + entry.getValue().stream()
                           .map(m -> m.getUnits())
                            .collect(Collectors.summingInt(m -> m.intValue())));

                });


    }
}
