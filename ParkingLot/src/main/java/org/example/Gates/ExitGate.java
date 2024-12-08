package org.example.Gates;

import lombok.Data;
import org.example.Constants.ParkingTicketStatus;
import org.example.Model.ParkingTicket;
import org.example.ParkingLot.ParkingLot;
import org.example.Payment.PaymentStrategy;
import org.example.Vehicle.Vehicle;

import java.sql.Timestamp;
import java.util.Objects;

@Data
public class ExitGate {
    // Exit gate class
    // it has an id
    // It has a function to process payment for a vehicle using the payment strategy that the user chose.
    // This function takes the vehicle and the payment strategy as the input, calculates the
    // total duration of parking using the parking ticket present in the vehicle object and calculates
    // the total cost using the total duration (in hours) and the parking rate per hour (retrieved from the
    // singleton parking lot class' instance).
    private String id;

    public ExitGate(String id){
        this.id = id;
    }

    public void processPayment(Vehicle vehicle, PaymentStrategy paymentStrategy){
        ParkingTicket ticket = vehicle.getParkingTicket();

        if (Objects.nonNull(ticket) || ticket.getParkingTicketStatus() != ParkingTicketStatus.ACTIVE) {
            throw new IllegalArgumentException("Invalid ticket.");
        }

        long durationMilliseconds = (new Timestamp(System.currentTimeMillis()).getTime()) - ticket.getIssuedAt().getTime();
        int hoursParked = (int) Math.ceil(durationMilliseconds / (1000.0 * 60 * 60));

        // We retrieve the parking rate from the instance of the singleton parking lot class
        int ratePerHour = ParkingLot.getInstance().getParkingRate();
        int totalCost = hoursParked * ratePerHour;

        paymentStrategy.processPayment(totalCost);
        ticket.setParkingTicketStatus(ParkingTicketStatus.PAID);
        System.out.println("Payment processed at " + id + " for ticket: " + ticket.getId());
    }
}
