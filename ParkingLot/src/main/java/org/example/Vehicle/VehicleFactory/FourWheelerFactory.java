package org.example.Vehicle.VehicleFactory;

import org.example.Constants.VehicleType;
import org.example.Vehicle.Car;
import org.example.Vehicle.Van;
import org.example.Vehicle.Vehicle;

public class FourWheelerFactory implements VehicleFactory{
    @Override
    public Vehicle createVehicle(String vehicleNumber, VehicleType type) {
        if(type.equals(VehicleType.CAR)){
            return new Car(vehicleNumber);
        }
        else if(type.equals(VehicleType.VAN)){
            return new Van(vehicleNumber);
        }
        else{
            throw new IllegalArgumentException("Invalid vehicle type");
        }
    }
}
