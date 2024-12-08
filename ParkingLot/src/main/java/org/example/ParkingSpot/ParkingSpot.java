package org.example.ParkingSpot;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.Constants.ParkingSpotType;
import org.example.Vehicle.Vehicle;

@Data
public abstract class ParkingSpot {
    // Parking Spot class => it contains id, type, vehicle, and isFree variables.
    // It contains methods for adding vehicle to the spot and
    // removing vehicle from the spot

    private String id;
    private final ParkingSpotType type;
    private Vehicle vehicle;
    private boolean isFree;

    protected ParkingSpot(String id, ParkingSpotType type) {
        this.id = id;
        this.type = type;
        this.isFree = true;
    }

    public boolean isFree(){
        return isFree;
    }

    public void assignVehicleToSpot(Vehicle vehicle){
        this.vehicle = vehicle;
        this.isFree = false;
    }

    public void removeVehicle(){
        this.vehicle = null;
        this.isFree = true;
    }
}
