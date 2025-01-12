package com.example.ElevatorSystem.dispatcher;

import com.example.ElevatorSystem.constants.Direction;
import com.example.ElevatorSystem.model.ElevatorController;
import com.example.ElevatorSystem.model.ElevatorSystem;
import lombok.Data;

@Data
public class InternalDispatcher {
    // This class represents an internal dispatcher
    // It has ha function submitRequest() that submits the request to the provided
    // elevator controller
    // The elevator controller will be provided in the function itself as the internal dispatcher
    // is used by internal button => the elevator controller to submit the request to is the
    // elevator controller of the internal button itself (i.e., the elevator in which the
    // internal button is present)
    // This is why internal button's pressButton() function has 3 arguments : floor, dir
    // and elevatorId
    // External button's pressButton() function has only 2 arguments : floor and dir => the
    // elevatorId is chosen using the elevator selection strategy.

    public  void submitRequest(int floor, Direction dir, int elevatorId)
    {
        for(ElevatorController eController: ElevatorSystem.INSTANCE.getElevatorControllerList())
        {
            if(eController.getId()== elevatorId)
            {
                eController.acceptRequest(floor, dir);
            }
        }
    }
}
