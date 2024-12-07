package org.example.ParkingSpot.ParkingSpotFactory;

import org.example.Constants.ParkingSpotType;
import org.example.ParkingSpot.*;
import org.example.Vehicle.ParkingSpot.*;

public class ParkingSpotFactoryImpl implements ParkingSpotFactory{

    @Override
    public ParkingSpot createParkingSpot(String id, String type) {
        if(type.equals(ParkingSpotType.HANDICAPPED)){
            return new HandicappedSpot(id);
        }
        else if(type.equals(ParkingSpotType.COMPACT)){
            return new CompactSpot(id);
        }
        else if(type.equals(ParkingSpotType.LARGE)){
            return new LargeSpot(id);
        }
        else if(type.equals(ParkingSpotType.MOTORBIKE)){
            return new MotorbikeSpot(id);
        }
        else{
            throw new IllegalArgumentException("Invalid parking spot type");
        }
    }
}
