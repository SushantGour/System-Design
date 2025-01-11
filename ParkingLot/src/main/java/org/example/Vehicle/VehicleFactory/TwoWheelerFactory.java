package org.example.Vehicle.VehicleFactory;

import org.example.Constants.VehicleType;
import org.example.Vehicle.MotorBike;
import org.example.Vehicle.Vehicle;

public class TwoWheelerFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle(String vehicleNumber, VehicleType type) {
        if(type.equals(VehicleType.MOTORBIKE)){
            return new MotorBike(vehicleNumber);
        }
        else {
            throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
