package com.example.ElevatorSystem;

import com.example.ElevatorSystem.constants.Direction;
import com.example.ElevatorSystem.model.ElevatorController;
import com.example.ElevatorSystem.model.ElevatorSystem;
import com.example.ElevatorSystem.model.Floor;
import com.example.ElevatorSystem.strategies.MinSeekTimeElevatorMovingStrategy;
import com.example.ElevatorSystem.strategies.MinSeekTimeSelectionStrategy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args){

        // Main function to instantiate and test our Elevator System

        // Get the ElevatorSystem signleton class' instance and set the strategies
        ElevatorSystem elevatorSystem= ElevatorSystem.INSTANCE;
        elevatorSystem.setElevatorSelectionStrategy(new MinSeekTimeSelectionStrategy());
        elevatorSystem.setElevatorMovingStrategy(new MinSeekTimeElevatorMovingStrategy());

        // add floors to our elevator system
        int totalFloors= 50;
        for(int i=0; i<=totalFloors; i++)
        {
            elevatorSystem.addFloor(new Floor(i));
        }

        log.info("No. of floors added : {}", totalFloors);

        // add elevators to our elevator system
        int totalElevators = 4;
        for(int i=1; i<=totalElevators; i++)
        {
            elevatorSystem.addElevator(new ElevatorController(i));
        }

        log.info("No. of elevators added : {}", totalElevators);

        //Working

        //Request 1
        log.info("Person at floor 1 presses UP Button");
        for(Floor floor: ElevatorSystem.INSTANCE.floors)
        {
            if(floor.getFloorNumber()==1)
                floor.pressButton(Direction.UP);
        }

        //Request 2
        log.info("Person at floor 5 presses UP Button");
        for(Floor floor: ElevatorSystem.INSTANCE.floors)
        {
            if(floor.getFloorNumber()==5)
                floor.pressButton(Direction.UP);
        }

        //Request 3
        log.info("Person presses 10 in elevator 2");
        for(ElevatorController elevatorController: ElevatorSystem.INSTANCE.getElevatorControllerList())
        {
            if(elevatorController.getId()==2)
                elevatorController.getElevatorCar().pressButton(10);
        }

        //Request 4
        log.info("Person presses 6 in elevator 2");
        for(ElevatorController elevatorController: ElevatorSystem.INSTANCE.getElevatorControllerList())
        {
            if(elevatorController.getId()==2)
                elevatorController.getElevatorCar().pressButton(6);
        }

        //Request 5
        log.info("Person at floor 7 presses DOWN Button");
        for(Floor floor: ElevatorSystem.INSTANCE.floors)
        {
            if(floor.getFloorNumber()==7)
                floor.pressButton(Direction.DOWN);
        }

        //Request 6
        log.info("Person presses 1 in elevator 3");
        for(ElevatorController elevatorController: ElevatorSystem.INSTANCE.getElevatorControllerList())
        {
            if(elevatorController.getId()==3)
                elevatorController.getElevatorCar().pressButton(1);
        }

    }
}
