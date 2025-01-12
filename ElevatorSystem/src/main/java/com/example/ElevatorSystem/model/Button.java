package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.constants.Direction;
import lombok.Data;

@Data
public abstract class Button {
    // Button class for representing a button

    // We have to set both methods as abstract and implement both in external
    // and internal button classes if we want to use Button reference and instantiate
    // it with InternalButton and ExternalButton whenever we want.

    // As if we don't define both the below methods here and define each in its
    // respective button class, then we cannot do the following in the Floor class :

//    private Button button;
//
//    public Floor(int floorNumber)
//    {
//        this.floorNumber= floorNumber;
//        button= new ExternalButton();
//    }
//
//    public void pressButton(Direction dir)
//    {
//        button.pressButton(floorNumber, dir);
//
//    }

    // as the pressButton() function doesn't know at compile time that the button object
    // is an external button => it takes it as a Button object only => the Button class has
    // no function named pressButton() in the Button class as we defined pressButton() for
    // both the button types in their own classes. So, in this case, we will have to specify
    // that the button object is an External button by doing : private ExternalButton button;

    // We are going with the first approach => i.e., defining both pressButton() methods as
    // abstract in the Button class and implementing both in both the child classes.

    public abstract void pressButton(int floor, Direction dir);

    public abstract void pressButton(int floor, Direction dir, int id);
}
