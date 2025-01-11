package org.example.Vehicle.VehicleFactory;

import org.example.Constants.VehicleType;
import org.example.Vehicle.Truck;
import org.example.Vehicle.Vehicle;

public class LargeVehicleFactory implements VehicleFactory{

    @Override
    public Vehicle createVehicle(String vehicleNumber, VehicleType type) {
        if(type.equals(VehicleType.TRUCK)){
            return new Truck(vehicleNumber);
        }
        else{
            throw new IllegalArgumentException("invalid vehicle type");
        }
    }
}
