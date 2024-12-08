package org.example.Account;

import org.example.Gates.EntryGate;
import org.example.Gates.ExitGate;
import org.example.ParkingDisplayBoard.ParkingDisplayBoard;
import org.example.ParkingFloor.ParkingFloor;
import org.example.ParkingLot.ParkingLot;
import org.example.ParkingSpot.ParkingSpot;

public class Admin extends Account{
    // Admin account
    // Methods => add parking floor, parking spot, display board, entry gate, exit gate.
    // We will use these methods to modify the singleton ParkingLot class.
    public void addParkingFloor(ParkingLot parkingLot, ParkingFloor floor){
        parkingLot.addParkingFloor(floor);
    };
    public void addParkingSpot(ParkingLot parkingLot, String floorName, ParkingSpot spot){
        parkingLot.addParkingSpot(floorName, spot);
    };
    public void addParkingDisplayBoard(ParkingLot parkingLot, String floorName, ParkingDisplayBoard displayBoard){
        parkingLot.addDisplayBoard(floorName, displayBoard);
    };
    public void addEntryGate(ParkingLot parkingLot, EntryGate entryGate){
        parkingLot.addEntryGate(entryGate);
    };
    public void addExitGate(ParkingLot parkingLot, ExitGate exitGate){
        parkingLot.addExitGate(exitGate);
    };
}
