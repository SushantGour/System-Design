package com.example.ElevatorSystem.dispatcher;

import com.example.ElevatorSystem.constants.Direction;
import com.example.ElevatorSystem.model.ElevatorController;
import com.example.ElevatorSystem.model.ElevatorSystem;

public class ExternalDispatcher {
    // It is a dispatcher class for submitting the external button press requests to the
    // elevator system.

    // It has a submitRequest() function that takes the request and finds the best
    // elevator controller to submit this request to using the elevator selection strategy.
    // Then it iterates in the singleton elevator system instance's elevator controller list
    // and finds the selected elevator controller object and submits the request to it.

    public  void submitRequest(int floor, Direction dir)
    {
        int elevatorId= ElevatorSystem.elevatorSelectionStrategy.selectElevator(floor, dir);
        System.out.println("Selected elevator " + elevatorId);
        for(ElevatorController eController: ElevatorSystem.INSTANCE.getElevatorControllerList())
        {
            if(eController.getId()== elevatorId)
            {
                eController.acceptRequest(floor, dir);
            }
        }
    }
}
