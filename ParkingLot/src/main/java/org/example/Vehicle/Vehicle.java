package org.example.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.Constants.VehicleType;
import org.example.Model.ParkingTicket;


@Data
public abstract class Vehicle {
    // Vehicle class => it contains vehicleNumber, vehicleType, and parkingTicket variables.
    // It contains method to assign parking ticket to the vehicle.
    private String vehicleNumber;
    private final VehicleType vehicleType;
    private ParkingTicket parkingTicket;

    public Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public void assignTicketToVehicle(ParkingTicket ticket){
        this.parkingTicket = ticket;
    }

}
