package org.example.Vehicle.VehicleFactory;

import org.example.Vehicle.Vehicle;

public interface VehicleFactory {
    public Vehicle createVehicle(String vehicleNumber, String type);
}
