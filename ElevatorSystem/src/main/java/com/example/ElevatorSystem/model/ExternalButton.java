package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.constants.Direction;
import com.example.ElevatorSystem.dispatcher.ExternalDispatcher;

public class ExternalButton extends Button{

    ExternalDispatcher externalDispatcher;

    public ExternalButton(){
        this.externalDispatcher = new ExternalDispatcher();
    }
    public void pressButton(int floor, Direction direction){
        externalDispatcher.submitRequest(floor,direction);
    }

    @Override
    public void pressButton(int floor, Direction dir, int id) {
        // This function is the internal button's implementation => no need to implement here
    }
}
