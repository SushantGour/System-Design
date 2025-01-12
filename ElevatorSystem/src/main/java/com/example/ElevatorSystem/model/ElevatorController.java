package com.example.ElevatorSystem.model;

import com.example.ElevatorSystem.constants.Direction;
import lombok.Data;

import java.util.PriorityQueue;

@Data
public class ElevatorController {
    // This is an elevator controller class
    // It contains id and elevator car object of the same id which it will control
    // It has a data structure to store the pending requests for this elevator controller
    // We are using a priority queue as we are using MinSeekTime strategy for selecting and moving
    // the elevator.

    // It has a function acceptRequest() that takes the request from the user and adds it to the
    // pending queue of the elevator controller. We then call the elevator moving strategy's move() function
    // and pass this elevator controller object as an argument to it. Now, the elevator
    // is moved according to the moving strategy.

    // In this example, we are using MinSeekTime strategy for both selecting and moving the elevator

    // Also, we move the elevator as soon as we get a new request for that elevator controller.
    // Also, we move the elevator by one step in a given call to the elevator strategy's move()
    // function. The reason for that is explained in the min seek time moving strategy class.

    // Now, let's look at the process of maintaining the pending requests for the elevator controller
    // according to the minimum seek time strategy.
    // In the minimum seek time strategy, we want to process those requests first whose target floors
    // are nearest to the elevator's current floor.
    // In our example, we make a priority queue with a custom comparator that compares the
    // seek time of the two requests based on the request's target floor, the elevator's current floor
    // and the elevator's current direction. The floors in the same direction are given higher priority
    // whereas the floors in the opposite direction are given lower priority.
    // Now, whenever we add a new request to the priority queue, the requests will be sorted according
    // to the given logic and we will get the minimum seek time request (with considering direction) at the
    // top of the priority queue.

    // Now, we will call the elevator moving strategy's move() method for the elevator controller.
    // This method will move the elevator one step and update the current floor and resort the priority queue
    // (as the current floor has changed).

    // This is how the minimum seek time strategy works (with considering direction)

    int id;
    ElevatorCar elevatorCar;

    PriorityQueue<Request> pendingRequestQueue;

    public ElevatorController(int id)
    {
        this.id= id;
        elevatorCar= new ElevatorCar(id);
        this.pendingRequestQueue = new PriorityQueue<>((r1, r2) -> {
            // Priority for r1
            int priority1 = calculatePriority(r1);
            // Priority for r2
            int priority2 = calculatePriority(r2);

            // Compare based on calculated priorities
            return Integer.compare(priority1, priority2);
        });
    }

    private int calculatePriority(Request request) {
        int distance = Math.abs(request.getFloor() - elevatorCar.getCurrentFloor());

        // If the elevator is idle, just return the distance
        if (elevatorCar.getDir() == Direction.NONE) {
            return distance;
        }

        boolean isSameDirection = (elevatorCar.getDir() == Direction.UP && request.getFloor() > elevatorCar.getCurrentFloor()) ||
                (elevatorCar.getDir() == Direction.DOWN && request.getFloor() < elevatorCar.getCurrentFloor());

        // If the request is in the same direction, prioritize it by distance
        if (isSameDirection) {
            return distance;
        }

        // If the request is in the opposite direction, deprioritize it (add a penalty)
        // As all the requests in the opposite direction have been added with the same penalty, they
        // will be sorted according to the distance only, but they will come after the
        // same direction requests in the priority queue.
        int penalty = 1000; // Arbitrary large value to lower priority
        return distance + penalty;
    }

    public void acceptRequest(int floor, Direction dir)
    {
        pendingRequestQueue.add(new Request(floor, dir));
        ElevatorSystem.elevatorMovingStrategy.moveElevator(this);
    }

}
