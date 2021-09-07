package com.elbicon.coderscampus;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class EntrySet {
    public Set<Map.Entry<Integer, List<Vehicle>>> GetYearSet(Map<Integer, List<Vehicle>> groupedByYear) {
        Set<Map.Entry<Integer, List<Vehicle>>> entrySet = groupedByYear.entrySet();
        return entrySet;
    }

    public Set<Map.Entry<String, List<Vehicle>>> GetYearMonthSet(Map<String, List<Vehicle>> groupedByYear) {
        Set<Map.Entry<String, List<Vehicle>>> entrySet = groupedByYear.entrySet();
        return entrySet;
    }
}
