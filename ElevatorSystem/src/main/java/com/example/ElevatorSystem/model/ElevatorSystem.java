package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.strategies.ElevatorMovingStrategy;
import com.example.ElevatorSystem.strategies.ElevatorSelectionStrategy;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ElevatorSystem {
    // This is the elevator system class.
    // It will control the whole system
    // We will make it a singleton class as we have only one building and hence only
    // one elevator system => it should be a singleton class that has only one object representing
    // the whole elevator system.

    // It has an elevator controller list containing the list of all the elevator controllers
    // It has a static selection strategy and moving strategy
    // It has a list of floors

    // It has the static singleton instance called INSTANCE that will represent the single
    // object of this class and will be used everywhere in the code to represent the
    // elevator system.

    // We will have to take care of the concurrent access to our elevator system class (its members)

    // It has some getter and setter functions defined below.

    private List<ElevatorController> elevatorControllerList= new ArrayList<ElevatorController>();
    public static ElevatorMovingStrategy elevatorMovingStrategy;
    public static ElevatorSelectionStrategy elevatorSelectionStrategy;
    public List<Floor> floors= new ArrayList<Floor>();

    public static ElevatorSystem INSTANCE= new ElevatorSystem();

    public void addElevator(ElevatorController e)
    {
        elevatorControllerList.add(e);
    }
    public void removeElevator(ElevatorController e)
    {
        elevatorControllerList.remove(e);
    }
    public void setElevatorMovingStrategy(ElevatorMovingStrategy elevatorMovingStrategy)
    {
        // This is a way to access the class' static members => by using the class reference
        // It is a better way than doing this.elevatorMovingStrategy = elevatorMovingStrategy
        ElevatorSystem.elevatorMovingStrategy = elevatorMovingStrategy;
    }
    public void setElevatorSelectionStrategy(ElevatorSelectionStrategy elevatorSelectionStrategy)
    {
        ElevatorSystem.elevatorSelectionStrategy = elevatorSelectionStrategy;
    }
    public  void addFloor(Floor floor)
    {
        floors.add(floor);
    }
}
