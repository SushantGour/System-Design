package org.example.ParkingFloor;

import lombok.Data;
import org.example.Constants.ParkingSpotType;
import org.example.Constants.VehicleType;
import org.example.ParkingDisplayBoard.ParkingDisplayBoard;
import org.example.ParkingSpot.*;
import org.example.Vehicle.Vehicle;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class ParkingFloor {
    // Parking floor class.
    // It has a name, and 4 separate hashmaps that stores the parking spots. Each hashmap is a map of
    // (String,ParkingSpot) where the key is the parking spot id and the value is the actual parking spot.
    // of each type that are present on the floor, and a display board.

    // The display board is a class that stores only 4 spots in it, one of each type that are free
    // The display board is used to display ]one currently free spot for each type.

    // Now, Parking floor has a constructor that takes name and display board.
    // It has a function to add parking slot on the floor. We just update the hashmap corresponding to
    // the added spot.
    // It has a function to assign a vehicle to a spot. It takes the vehicle and the spot id as the parameter
    // and assigns the vehicle to the spot and update the display board. To update the display board, we do =>
    // Say we assigned a vehicle to a Handicapped spot. Now, as we know that the display board stores the
    // currently free spots (one spot for each type) => we should check that whether the handicapped spot in
    // the display board is equal to the handicapped spot that just got filled or not. If yes, then we should
    // update the handicapped spot in the display board to store a currently free handicapped spot
    // (by checking in the handicapped spots storing hashmap), else we should do nothing.

    // It has a function to remove a vehicle from a spot.
    // It removes the vehicle and updates the display board.
    // To update the display board, we should check whether the same type spot in the
    // display board is free or not => if it is free => do nothing, if it is not free
    // (for the case when all the spots of that type are not free in tha floor), then assign the
    // currently freed spot to that spot.

    // It has a isFull(){} method.
    // It returns true if all the parking spots of the floor are occupied.

    private String name;
    private HashMap<String, HandicappedSpot> handicappedSpots;
    private HashMap<String, CompactSpot> compactSpots;
    private HashMap<String, LargeSpot> largeSpots;
    private HashMap<String, MotorbikeSpot> motorbikeSpots;
    private ParkingDisplayBoard displayBoard;

    public ParkingFloor(String name, ParkingDisplayBoard displayBoard) {
        this.name = name;
        this.handicappedSpots = new HashMap<>();
        this.compactSpots = new HashMap<>();
        this.largeSpots = new HashMap<>();
        this.motorbikeSpots = new HashMap<>();
        this.displayBoard = displayBoard;
    }

    public void addParkingSpot(ParkingSpot spot) {
        switch (spot.getType()) {
            case HANDICAPPED:
                handicappedSpots.put(spot.getId(), (HandicappedSpot) spot);
                break;
            case COMPACT:
                compactSpots.put(spot.getId(), (CompactSpot) spot);
                break;
            case LARGE:
                largeSpots.put(spot.getId(), (LargeSpot) spot);
                break;
            case MOTORBIKE:
                motorbikeSpots.put(spot.getId(), (MotorbikeSpot) spot);
                break;
            default:
                throw new IllegalArgumentException("Wrong parking spot type!");
        }
    }

    public void assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot) {
        spot.assignVehicleToSpot(vehicle);
        switch (spot.getType()) {
            case HANDICAPPED:
                updateDisplayBoardForHandicapped(spot);
                break;
            case COMPACT:
                updateDisplayBoardForCompact(spot);
                break;
            case LARGE:
                //updateDisplayBoardForLarge(spot);
                break;
            case MOTORBIKE:
                //updateDisplayBoardForMotorbike(spot);
                break;
            default:
                throw new IllegalArgumentException("Wrong parking spot type!");
        }
    }

    private void updateDisplayBoardForHandicapped(ParkingSpot spot) {
        if (this.displayBoard.getHandicappedFreeSpot().getId().equals(spot.getId())) {
            // find another free handicapped parking and assign to displayBoard
            for (String key : handicappedSpots.keySet()) {
                if (handicappedSpots.get(key).isFree()) {
                    this.displayBoard.setHandicappedFreeSpot(handicappedSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    private void updateDisplayBoardForCompact(ParkingSpot spot) {
        if (this.displayBoard.getCompactFreeSpot().getId().equals(spot.getId())) {
            // find another free compact parking and assign to displayBoard
            for (String key : compactSpots.keySet()) {
                if (compactSpots.get(key).isFree()) {
                    this.displayBoard.setCompactFreeSpot(compactSpots.get(key));
                }
            }
            this.displayBoard.showEmptySpotNumber();
        }
    }

    // Similarly, we can code updateDisplayBoardForLarge and updateDisplayBoardForMotorbike.

    public void removeVehicleFromSpot(ParkingSpot spot) {
        spot.removeVehicle();
        switch (spot.getType()) {
            case HANDICAPPED:
                if(!displayBoard.getHandicappedFreeSpot().isFree()){
                    displayBoard.setHandicappedFreeSpot((HandicappedSpot) spot);
                }
                break;
            case COMPACT:
                if(!displayBoard.getCompactFreeSpot().isFree()){
                    displayBoard.setCompactFreeSpot((CompactSpot) spot);
                }
                break;
            case LARGE:
                if(!displayBoard.getLargeFreeSpot().isFree()){
                    displayBoard.setLargeFreeSpot((LargeSpot) spot);
                }
                break;
            case MOTORBIKE:
                if(!displayBoard.getMotorbikeFreeSpot().isFree()){
                    displayBoard.setMotorbikeFreeSpot((MotorbikeSpot) spot);
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong parking spot type!");
        }
    }

    public boolean isFull() {
        return checkIsHandicappedFull() && checkIsCompactFull() && checkIsLargeFull() && checkIsMotorBikeFull();
    }

    private boolean checkIsHandicappedFull() {
        for(HandicappedSpot handicappedSpot : handicappedSpots.values()){
            if(handicappedSpot.isFree()){
                return false;
            }
        }
        return true;
    }

    private boolean checkIsCompactFull() {
        for(CompactSpot compactSpot : compactSpots.values()){
            if(compactSpot.isFree()){
                return false;
            }
        }
        return true;
    }

    private boolean checkIsLargeFull() {
        for(LargeSpot largeSpot : largeSpots.values()){
            if(largeSpot.isFree()){
                return false;
            }
        }
        return true;
    }

    private boolean checkIsMotorBikeFull() {
        for(MotorbikeSpot motorbikeSpot : motorbikeSpots.values()){
            if(motorbikeSpot.isFree()){
                return false;
            }
        }
        return true;
    }

    }
