package com.example.ElevatorSystem.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Door {
    // Door class for representing a door
    public void open(){
        log.info("Door opens");
    }
    public void close(){
        log.info("Door closes");
    }
}
