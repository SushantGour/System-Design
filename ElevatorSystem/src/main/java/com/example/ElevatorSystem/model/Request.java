package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.constants.Direction;
import lombok.Data;

@Data
public class Request {
    int floor;
    Direction dir;

    public Request(int floor, Direction dir){
        this.floor = floor;
        this.dir = dir;
    }
}
