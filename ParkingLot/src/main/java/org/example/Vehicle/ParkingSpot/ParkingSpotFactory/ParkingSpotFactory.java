package org.example.Vehicle.ParkingSpot.ParkingSpotFactory;

import org.example.Vehicle.ParkingSpot.ParkingSpot;

public interface ParkingSpotFactory {
    ParkingSpot createParkingSpot(String id, String type);
}
