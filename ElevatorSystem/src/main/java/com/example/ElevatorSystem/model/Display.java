package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.constants.Direction;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Display {
    // Display object to display where the elevator car is going
    // It contains floor and direction data
    // It has a display function that prints the floor and direction to/in which the
    // elevator car is going.
    int floor;
    Direction direction;

    public void display(){
        log.info("Elevator going to floor {} in {} direction", floor, direction.name());
    }
}
