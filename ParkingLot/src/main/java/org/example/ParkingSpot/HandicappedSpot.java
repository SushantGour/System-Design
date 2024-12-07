package org.example.ParkingSpot;

import org.example.Constants.ParkingSpotType;

public class HandicappedSpot extends ParkingSpot{
    public HandicappedSpot(String id){
        super(id, ParkingSpotType.HANDICAPPED);
    }
}
