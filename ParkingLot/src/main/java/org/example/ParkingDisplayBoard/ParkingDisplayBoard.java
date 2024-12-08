package org.example.ParkingDisplayBoard;

import lombok.Data;
import org.example.ParkingSpot.CompactSpot;
import org.example.ParkingSpot.HandicappedSpot;
import org.example.ParkingSpot.LargeSpot;
import org.example.ParkingSpot.MotorbikeSpot;

@Data
public class ParkingDisplayBoard {
    // Parking display board class
    // It contains an id, and 4 variables that store one currently free spot on a floor
    // for a particular type each.

    // It has a function that prints all the 4 free spots of each type.

    private String id;
    private HandicappedSpot handicappedFreeSpot;
    private CompactSpot compactFreeSpot;
    private LargeSpot largeFreeSpot;
    private MotorbikeSpot motorbikeFreeSpot;

    public ParkingDisplayBoard(String id){
        this.id = id;
    }

    public void showEmptySpotNumber() {
        String message = "";

        if (handicappedFreeSpot.isFree()) {
            message += "Free Handicapped: " + handicappedFreeSpot.getId();
        } else {
            message += "Handicapped is full";
        }
        message += System.lineSeparator();

        if (compactFreeSpot.isFree()) {
            message += "Free Compact: " + compactFreeSpot.getId();
        } else {
            message += "Compact is full";
        }
        message += System.lineSeparator();

        if (largeFreeSpot.isFree()) {
            message += "Free Large: " + largeFreeSpot.getId();
        } else {
            message += "Large is full";
        }
        message += System.lineSeparator();

        if (motorbikeFreeSpot.isFree()) {
            message += "Free Motorbike: " + motorbikeFreeSpot.getId();
        } else {
            message += "Motorbike is full";
        }
        message += System.lineSeparator();

        System.out.println(message);
    }
}
