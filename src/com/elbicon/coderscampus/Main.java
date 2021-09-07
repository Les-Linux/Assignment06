package com.elbicon.coderscampus;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here
        FileServiceImp vehicle = new FileServiceImp();
        final File model3File = new File("./sources/model3.csv");
        final File modelSFile = new File("./sources/modelS.csv");
        final File modelXFile = new File("./sources/modelX.csv");


       VehicleSales vehicleSalesReport = new VehicleSales();

        ArrayList<Vehicle> model3 = vehicle.Import(model3File);
        ArrayList<Vehicle> modelS = vehicle.Import(modelSFile);
        ArrayList<Vehicle> modelX = vehicle.Import(modelXFile);


        // Model3
        vehicleSalesReport.Report(model3,model3File.toString());

        //ModelS
        vehicleSalesReport.Report(modelS,modelSFile.toString());

        //ModelX
        vehicleSalesReport.Report(modelX,modelXFile.toString());
    }
}
