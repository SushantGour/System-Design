package com.example.ElevatorSystem.strategies;

import com.example.ElevatorSystem.constants.Direction;
import com.example.ElevatorSystem.model.ElevatorController;
import com.example.ElevatorSystem.model.ElevatorSystem;

public class MinSeekTimeSelectionStrategy extends ElevatorSelectionStrategy{

    // We can see that the request's direction is never used in calculating the see time
    // in case of the minimum seek time strategy as the seek time is calculated using
    // the request's target floor and the elevator's current floor and the elevator's current direction.
    // But, we should not remove the request's direction from the function argument of the
    // elevator selection strategy parent class as it might be used in the calculations for some other
    // strategies (i.e., for some other elevator strategy impls).
    @Override
    public int selectElevator(int floor, Direction direction) {
        ElevatorController bestElevator = null;
        int minimumSeekTime = Integer.MAX_VALUE;

        for (ElevatorController elevator : ElevatorSystem.INSTANCE.getElevatorControllerList()) {
            // Calculate seek time
            int seekTime = calculateSeekTime(elevator, floor, direction);

            // Update the best elevator if this one has a lower seek time
            if (seekTime < minimumSeekTime) {
                minimumSeekTime = seekTime;
                bestElevator = elevator;
            }
        }

        assert bestElevator != null;
        return bestElevator.getId();
    }

    private int calculateSeekTime(ElevatorController elevator, int requestedFloor, Direction direction) {
        int currentFloor = elevator.getElevatorCar().getCurrentFloor();
        Direction currentDirection = elevator.getElevatorCar().getDir();

        if (currentDirection == Direction.NONE) {
            // If the elevator is idle, seek time is simply the absolute difference in floors
            return Math.abs(currentFloor - requestedFloor);
        } else if ((currentDirection == Direction.UP && requestedFloor >= currentFloor) ||
                (currentDirection == Direction.DOWN && requestedFloor <= currentFloor)) {
            // If the elevator is moving in the same direction as the request
            return Math.abs(currentFloor - requestedFloor);
        } else {
            // If the elevator is moving in the opposite direction, add a penalty for turnaround
            int turnaroundPenalty = 1000; // Arbitrary high value for turning around
            return Math.abs(currentFloor - requestedFloor) + turnaroundPenalty;
        }
    }
}
