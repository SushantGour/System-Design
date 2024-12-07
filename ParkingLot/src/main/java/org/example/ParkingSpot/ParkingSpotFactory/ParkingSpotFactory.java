package org.example.ParkingSpot.ParkingSpotFactory;

import org.example.ParkingSpot.ParkingSpot;

public interface ParkingSpotFactory {
    ParkingSpot createParkingSpot(String id, String type);
}
