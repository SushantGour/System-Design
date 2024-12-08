package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Constants.ParkingTicketStatus;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingTicket {
    // Parking ticket class for storing parking ticket details.
    private String id;
    private Timestamp issuedAt;
    private Timestamp paidAt;
    private ParkingTicketStatus parkingTicketStatus;

    public ParkingTicket(String id){
        this.id = id;
        this.parkingTicketStatus = ParkingTicketStatus.ACTIVE;
    }
}
