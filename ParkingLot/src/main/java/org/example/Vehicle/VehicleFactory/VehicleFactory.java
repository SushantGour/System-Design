package org.example.Vehicle.VehicleFactory;

import org.example.Constants.VehicleType;
import org.example.Vehicle.Vehicle;

public interface VehicleFactory {
    // Factory Method Design Pattern => Vehicle factory to create different types of vehicles.
    public Vehicle createVehicle(String vehicleNumber, VehicleType type);
}
