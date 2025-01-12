package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.constants.Direction;
import lombok.Data;

@Data
public class Floor {
    // Floor class representing a floor.
    // It has floorNumber, display, and button (external button).
    // It has a function pressButton() that calls the external button's pressButton() function

    private int floorNumber;
    private Display display;
    private Button button;

    public Floor(int floorNumber)
    {
        this.floorNumber= floorNumber;
        button= new ExternalButton();
    }

    public void pressButton(Direction dir)
    {
        button.pressButton(floorNumber, dir);

    }

    // Sets and shows the display
    private  void setDisplay(int floor, Direction dir)
    {
        display.setDirection(dir);
        display.setFloor(floor);
        display.display();;
    }
}
