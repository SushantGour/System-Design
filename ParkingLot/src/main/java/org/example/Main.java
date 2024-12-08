package org.example;

import org.example.Constants.ParkingSpotType;
import org.example.Constants.VehicleType;
import org.example.Gates.EntryGate;
import org.example.Gates.ExitGate;
import org.example.ParkingDisplayBoard.ParkingDisplayBoard;
import org.example.ParkingFloor.ParkingFloor;
import org.example.ParkingLot.ParkingLot;
import org.example.ParkingSpot.ParkingSpot;
import org.example.ParkingSpot.ParkingSpotFactory.ParkingSpotFactory;
import org.example.ParkingSpot.ParkingSpotFactory.ParkingSpotFactoryImpl;
import org.example.Payment.CashPaymentStrategy;
import org.example.Payment.PaymentStrategy;
import org.example.Vehicle.Vehicle;
import org.example.Vehicle.VehicleFactory.FourWheelerFactory;
import org.example.Vehicle.VehicleFactory.VehicleFactory;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Initialize the parking lot.
        ParkingLot parkingLot = ParkingLot.initializeInstance("Downtown Parking", "123 Main St", 50);

        // Add parking floors
        ParkingFloor floor1 = new ParkingFloor("Floor1", new ParkingDisplayBoard("displayBoard1"));
        ParkingFloor floor2 = new ParkingFloor("Floor2", new ParkingDisplayBoard("displayBoard2"));
        parkingLot.addParkingFloor(floor1);
        parkingLot.addParkingFloor(floor2);

        // Add parking spots to floors.
        ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactoryImpl();
        ParkingSpot handicappedSpot = parkingSpotFactory.createParkingSpot("parkingSpot1", String.valueOf(ParkingSpotType.HANDICAPPED));
        ParkingSpot largeSpot = parkingSpotFactory.createParkingSpot("parkingSpot2", String.valueOf(ParkingSpotType.COMPACT));
        parkingLot.addParkingSpot("Floor1", handicappedSpot);
        parkingLot.addParkingSpot("Floor2", largeSpot);

        // Add entry and exit gates.
        EntryGate gate1 = new EntryGate("Gate1");
        ExitGate gate2 = new ExitGate("Gate2");
        parkingLot.addEntryGate(gate1);
        parkingLot.addExitGate(gate2);

        // Test entry gate functionality.
        VehicleFactory vehicleFactory = new FourWheelerFactory();
        Vehicle car = vehicleFactory.createVehicle("HR-35-BP07", String.valueOf(VehicleType.CAR));
        gate1.generateTicket(car); // Generate ticket at entry gate

        // Test exit gate functionality.
        PaymentStrategy paymentStrategy = new CashPaymentStrategy();
        gate2.processPayment(car, paymentStrategy); // Calculate payment and process it
    }
}