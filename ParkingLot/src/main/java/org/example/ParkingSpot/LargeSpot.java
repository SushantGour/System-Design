package org.example.ParkingSpot;

import org.example.Constants.ParkingSpotType;

public class LargeSpot extends ParkingSpot{
    public LargeSpot(String id){
        super(id, ParkingSpotType.LARGE);
    }
}
