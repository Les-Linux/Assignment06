package com.elbicon.coderscampus;

import java.time.LocalDate;

public class Vehicle {
    private LocalDate salesYear;
    private String model;
    private int units;

    public LocalDate getSalesYear() {
        return salesYear;
    }

    public void setSalesYear(LocalDate salesYear) {
        this.salesYear = salesYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }


}
