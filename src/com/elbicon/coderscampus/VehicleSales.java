package com.elbicon.coderscampus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VehicleSales {
    public void Report(ArrayList<Vehicle> vehicles, String model) {
        Grouping grouping = new Grouping();
        EntrySet entrySet = new EntrySet();
        try {
            ConsoleOutput.Header(FileServiceImp.Model(model));
            Map<Integer, List<Vehicle>> groupedByYear = grouping.ByYear(vehicles);
            Map<String, List<Vehicle>> groupedByYearMonth = grouping.ByYearMonth(vehicles);

            //Set<Map.Entry<Integer,List<Vehicle>>> entrySetYearModel3 = EntrySet(groupedByYearModel3);
            Set<Map.Entry<Integer, List<Vehicle>>> entrySetYear = entrySet.GetYearSet(groupedByYear);
            Set<Map.Entry<String, List<Vehicle>>> entrySetYearMonth = entrySet.GetYearMonthSet(groupedByYearMonth);

            ConsoleOutput.Body(entrySetYear);
            ConsoleOutput.Footer(entrySetYearMonth, FileServiceImp.Model(model));
        } catch (Exception e) {
            System.out.println("Exception Caught: " + e.getMessage());
        }
    }
}
