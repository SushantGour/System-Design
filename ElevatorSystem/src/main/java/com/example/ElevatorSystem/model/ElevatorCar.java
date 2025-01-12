package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.constants.Direction;
import lombok.Data;

@Data
public class ElevatorCar {
    // It is a class representing the elevator car
    // It is a dumb object which is controlled by the elevator controller object
    // It has id, door, display, button (internal button), current floor and direction data

    int id;
    Door door;
    Display display;
    Button button;
    int currentFloor;
    private Direction dir;


    public ElevatorCar(int id)
    {
        this.id= id;
        door= new Door();
        display= new Display();
        currentFloor= 0;
        dir= Direction.NONE;
        button= new InternalButton();

    }

    public void pressButton(int floor)
    {
        // Whenever a user presses the internal button with a floor number,
        // we check if the entered floor is above or below the
        // current floor and set direction accordingly.
        // Now, we submit the request using the pressButton() function for the
        // floor number and direction.
        // Now, the request will go to the dispatcher which will submit the request to the
        // pending request data structure of the current elevator controller.
        // Then the elevator is moved according to the elevator moving strategy.

        // Calculate required direction
        Direction dir= Direction.NONE;
        if(floor>currentFloor)
            dir= Direction.UP;
        else if(floor<currentFloor)
            dir= Direction.DOWN;
        button.pressButton(floor, dir, id);
    }

    // Set the display and show the display
    private void setDisplay()
    {
        display.setFloor(currentFloor);
        display.setDirection(dir);
        display.display();

    }
}
