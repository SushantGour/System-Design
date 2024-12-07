package org.example.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Constants.VehicleType;
import org.example.Model.ParkingTicket;


public abstract class Vehicle {
    private String vehicleNumber;
    private final VehicleType vehicleType;
    private ParkingTicket parkingTicket;

    public Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    void assignTicketToVehicle(ParkingTicket ticket){
        this.parkingTicket = ticket;
    }

}
