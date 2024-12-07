package org.example.ParkingSpot;

import org.example.Constants.ParkingSpotType;

public abstract class ParkingSpot {

    private String id;
    private final ParkingSpotType type;
    private boolean isFree;

    protected ParkingSpot(String id, ParkingSpotType type) {
        this.id = id;
        this.type = type;
        this.isFree = false;
    }
}
