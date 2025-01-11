package org.example.ParkingLot;

import lombok.Data;
import org.example.Constants.VehicleType;
import org.example.Gates.EntryGate;
import org.example.Gates.ExitGate;
import org.example.ParkingDisplayBoard.ParkingDisplayBoard;
import org.example.ParkingFloor.ParkingFloor;
import org.example.ParkingSpot.ParkingSpot;

import java.util.HashMap;

@Data
public class ParkingLot {
    // Singleton parking lot class
    // It follows the Singleton design pattern.
    // It has a name, address, and a parkingRate variable.
    // It has hashmaps that store parking floors, entry gates and exit gates.

    // It has a private constructor to prevent direct instantiation.
    // It has a method to initialize the singleton instance.
    // It has a method to get the singleton instance.
    // It has methods to add parking floors, parking spots, display boards, etc. We can do this from this class
    // as well as from the Admin class (but there, we have to create an admin object and call its methods
    // by passing the singleton parking lot object as an argument as its methods takes ParkingLot object
    // as an argument).
    // It has a method to check if parking spots are full or not.

    // All the methods are synchronized as it is a singleton class => it has only one instance
    // that can be accessed from multiple places simultaneously => make the singleton class' functions
    // as synchronized => This allows only one thread to execute the function at a time.

    // Singleton instance
    private static ParkingLot instance;

    // Parking lot details
    private String name;
    private String address;
    private int parkingRate;

    // Parking structure and entry/exit panels
    private HashMap<String, ParkingFloor> parkingFloors;
    private HashMap<String, EntryGate> entryGates;
    private HashMap<String, ExitGate> exitGates;

    // Private constructor to prevent direct instantiation
    private ParkingLot(String name, String address, int parkingRate) {
        this.name = name;
        this.address = address;
        this.parkingRate = parkingRate;
        this.parkingFloors = new HashMap<>();
        this.entryGates = new HashMap<>();
        this.exitGates = new HashMap<>();
    }

    // Singleton initialization method
    // It is used to initialize the parking lot instance
    public static synchronized ParkingLot initializeInstance(String name, String address, int parkingRate) {
        if (instance == null) {
            instance = new ParkingLot(name, address, parkingRate);
        }
        return instance;
    }

    // Singleton retrieval method
    // It is used to get the singleton parking lot instance
    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ParkingLot is not initialized yet!");
        }
        return instance;
    }

    // Methods for managing parking floors
    public synchronized void addParkingFloor(ParkingFloor floor) {
        if (!parkingFloors.containsKey(floor.getName())) {
            parkingFloors.put(floor.getName(), floor);
            System.out.println("Parking floor " + floor.getName() + " added successfully.");
        } else {
            System.out.println("Parking floor " + floor.getName() + " already exists.");
        }
    }

    public synchronized ParkingFloor getParkingFloor(String floorName) {
        return parkingFloors.get(floorName);
    }

    // Methods for managing entry gates
    public synchronized void addEntryGate(EntryGate entryGate) {
        if (!entryGates.containsKey(entryGate.getId())) {
            entryGates.put(entryGate.getId(), entryGate);
            System.out.println("Entry gate " + entryGate.getId() + " added successfully.");
        } else {
            System.out.println("Entry gate " + entryGate.getId() + " already exists.");
        }
    }

    public synchronized EntryGate getEntryGate(String gateId) {
        return entryGates.get(gateId);
    }

    // Methods for managing exit gates
    public synchronized void addExitGate(ExitGate exitGate) {
        if (!exitGates.containsKey(exitGate.getId())) {
            exitGates.put(exitGate.getId(), exitGate);
            System.out.println("Exit gate " + exitGate.getId() + " added successfully.");
        } else {
            System.out.println("Exit gate " + exitGate.getId() + " already exists.");
        }
    }

    public synchronized ExitGate getExitGate(String gateId) {
        return exitGates.get(gateId);
    }

    // Additional parking spot management methods
    public synchronized void addParkingSpot(String floorName, ParkingSpot spot) {
        ParkingFloor floor = parkingFloors.get(floorName);
        if (floor != null) {
            floor.addParkingSpot(spot);
            System.out.println("Parking spot " + spot.getId() + " added to floor " + floorName + ".");
        } else {
            System.out.println("Parking floor " + floorName + " does not exist.");
        }
    }

    public void addDisplayBoard(String floorName, ParkingDisplayBoard displayBoard) {
        ParkingFloor floor = parkingFloors.get(floorName);
        if(floor != null && floor.getDisplayBoard() != null){
            floor.setDisplayBoard(displayBoard);
            System.out.println("Parking display board " + displayBoard.getId() + " added to floor " + floorName + ".");
        }
        else{
            if(floor == null){
                System.out.println("Parking floor " + floorName + " does not exist.");
            }
            else{
                System.out.println("Parking floor " + floorName + " already has a display board");
            }
        }
    }

    public synchronized boolean isParkingFull() {
        for (ParkingFloor floor : parkingFloors.values()) {
            if (!floor.isFull()) {
                return false;
            }
        }
        return true;
    }
}
