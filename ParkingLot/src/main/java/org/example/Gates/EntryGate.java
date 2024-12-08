package org.example.Gates;

import lombok.Data;
import org.example.Model.ParkingTicket;
import org.example.Vehicle.Vehicle;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class EntryGate {
    // Entry gate class
    // It has an id.
    // It has a function to generate a new ticket for a vehicle and assign it to it.
    // It generates a new ticket and assigns it to the vehicle.
    private String id;

    public EntryGate(String id){
        this.id = id;
    }

    public ParkingTicket generateTicket(Vehicle vehicle){
        // Generating unique id for the ticket
        String id = "TICKET-" + System.currentTimeMillis();
        ParkingTicket parkingTicket = new ParkingTicket(id);

        parkingTicket.setIssuedAt(new Timestamp(System.currentTimeMillis()));
        vehicle.assignTicketToVehicle(parkingTicket);

        return parkingTicket;
    }
}
