package org.example.ParkingSpot;

import org.example.Constants.ParkingSpotType;

public class CompactSpot extends ParkingSpot{
    public CompactSpot(String id){
        super(id, ParkingSpotType.COMPACT);
    }
}