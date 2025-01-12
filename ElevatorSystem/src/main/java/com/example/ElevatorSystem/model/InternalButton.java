package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.constants.Direction;
import com.example.ElevatorSystem.dispatcher.InternalDispatcher;
import lombok.Data;

@Data
public class InternalButton extends Button {
    InternalDispatcher internalDispatcher;

    public InternalButton(){
        this.internalDispatcher = new InternalDispatcher();
    }

    public void pressButton(int floor, Direction dir, int id){
        internalDispatcher.submitRequest(floor,dir,id);
    }

    @Override
    public void pressButton(int floor, Direction dir) {
        // This function is the external button's implementation => no need to implement here
    }
}
