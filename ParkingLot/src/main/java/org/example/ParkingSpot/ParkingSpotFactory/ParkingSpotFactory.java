package org.example.ParkingSpot.ParkingSpotFactory;

import org.example.Constants.ParkingSpotType;
import org.example.ParkingSpot.ParkingSpot;

public interface ParkingSpotFactory {
    // Factory Method Design Pattern => Parking spot factory to create different parking spots.
    ParkingSpot createParkingSpot(String id, ParkingSpotType type);
}
