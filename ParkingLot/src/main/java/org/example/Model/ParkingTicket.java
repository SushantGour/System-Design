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
    private String id;
    private Timestamp issuedAt;
    private Timestamp paidAt;
    private ParkingTicketStatus parkingTicketStatus;
}
