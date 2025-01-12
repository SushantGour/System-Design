package com.example.ElevatorSystem.strategies;

import com.example.ElevatorSystem.constants.Direction;
import com.example.ElevatorSystem.model.ElevatorCar;
import com.example.ElevatorSystem.model.ElevatorController;
import com.example.ElevatorSystem.model.Request;
import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

@Slf4j
public class MinSeekTimeElevatorMovingStrategy extends ElevatorMovingStrategy{

    // In the MinSeekTimeStrategy, we will move the elevator according to the minimum seek time
    // of the pending requests in the elevator's pending requests.
    // As we have used a priority queue in the elevator controller to store the pending requests for
    // that elevator in order of the seek time of the requests, we just need to pull the request at the
    // front of the priority queue, fulfill it and then sort the priority queue (as the elevator's current
    // floor has changed). This is how we implement the min seek time moving strategy.

    // Now, the question is that why we are fulfilling only one request in one call to the
    // moveElevator() function =>
    // Ans = It is because the moveElevator() function for an elevator can be called by multiple users
    // concurrently, i.e., for multiple users, the best elevator can turn out to be this elevator.
    // Now, if in the moveElevator() function, we move the elevator till we fulfill all the requests,
    // then in case of concurrent calls to the moveElevator() function, the
    // elevator moves can get corrupted.

    // Now, you will say that make the moveElevator() function synchronized. Then in that case, the first
    // call to the moveElevator() function will take a lock on the elevator controller object =>
    // Till all the current requests that are there at the first call of the moveElevator() function
    // are fulfilled, no other thread can access the elevator controller object => new requests can't be
    // added to the elevator controller's queue => there may be some requests
    // that can come during the time when the elevator controller object is locked that have
    // less seek time than the current requests that are being fulfilled right now.

    // So, the best way is to make the moveElevator() function synchronized and only process one
    // request in one moveElevator() function call. Now, this make sure that all the requests
    // are fulfilled and the elevator controller object doesn't remain locked for a long time.
    // Also, as after processing each request, the elevator reaches a new floor => the priority queue
    // order may change as the elevator's current floor has changed => this was not taken care of
    // in the previous approach that we discussed (the one where we make the
    // moveElevator() function synchronized and fulfill all the requests in a moveElevator() call),
    // But it is taken care of in the current approach as we can re-sort the priority queue after
    // fulfilling each request.
    // Also, as we are fulfilling one request in one call to the moveElevator() function, new requests
    // can be added to the elevator controller's pending request queue as the lock on the
    // elevator controller gets released after the moveElevator() function call which only fulfills
    // one request.

    // Now, you may ask how does this approach makes sure that all the requests are fulfilled =>
    // Ans = as we call the moveElevator() function whenever a new request is added, so this guarantees
    // that there will be as many calls to the moveElevator() function as there are requests in the
    // elevator controller's pending request queue => each request will get fulfilled in one of the
    // moveElevator() function calls.

    // This is how we implement the minimum seek time moving strategy.


    @Override
    public synchronized void moveElevator(ElevatorController elevatorController) {
        PriorityQueue<Request> pendingRequestQueue = elevatorController.getPendingRequestQueue();

        if (!pendingRequestQueue.isEmpty()) {
            // The top of the priority queue will contain the request with the mimimum seek time
            // according to the elevator's current floor
            // We will just process the request at the front of the priority queue and then
            // update the elevator's current floor and current direction and then resort the
            // priority queue as the elevator's current floor has changed
            // Processing a request means taking the elevator to that request's target floor directly
            Request nextRequest = pendingRequestQueue.poll();

            if (nextRequest != null) {
                // Move directly to the target floor
                log.info("Elevator " + elevatorController.getElevatorCar().getId() + " moving from floor " + elevatorController.getElevatorCar().getCurrentFloor() +
                        " to floor " + nextRequest.getFloor());

                // Update the elevator's position
                elevatorController.getElevatorCar().setCurrentFloor(nextRequest.getFloor());

                // Update the direction based on the target floor
                if (elevatorController.getElevatorCar().getCurrentFloor() < nextRequest.getFloor()) {
                    elevatorController.getElevatorCar().setDir(Direction.UP);
                } else if (elevatorController.getElevatorCar().getCurrentFloor() > nextRequest.getFloor()) {
                    elevatorController.getElevatorCar().setDir(Direction.DOWN);
                } else {

                    elevatorController.getElevatorCar().setDir(Direction.NONE);
                }

                log.info("Elevator " + elevatorController.getElevatorCar().getId() + " reached floor " + elevatorController.getElevatorCar().getCurrentFloor());
                log.info("Request fulfilled at floor: " + elevatorController.getElevatorCar().getCurrentFloor());

                // Re-sort the queue to account for the updated floor
                reSortQueue(elevatorController);
            }
        }

        // Reset direction when no more requests remain
        elevatorController.getElevatorCar().setDir(Direction.NONE);
        log.info("Elevator " + elevatorController.getElevatorCar().getId() + " is now idle.");
    }

    private void reSortQueue(ElevatorController elevatorController) {
        // Re-sort the priority queue as the elevator's current floor has changed
        // We make a new priority queue and add the elements of the current priority queue in it and then
        // set the pending request queue of the elevator controller to the new priority queue
        PriorityQueue<Request> updatedQueue = new PriorityQueue<>((r1, r2) -> {
            // Priority for r1
            int priority1 = calculatePriority(r1, elevatorController);
            // Priority for r2
            int priority2 = calculatePriority(r2, elevatorController);

            // Compare based on calculated priorities
            return Integer.compare(priority1, priority2);
        });
        updatedQueue.addAll(elevatorController.getPendingRequestQueue());
        elevatorController.setPendingRequestQueue(updatedQueue);
    }

    private int calculatePriority(Request request, ElevatorController elevatorController) {
        int distance = Math.abs(request.getFloor() - elevatorController.getElevatorCar().getCurrentFloor());

        // If the elevator is idle, just return the distance
        if (elevatorController.getElevatorCar().getDir() == Direction.NONE) {
            return distance;
        }

        boolean isSameDirection = (elevatorController.getElevatorCar().getDir() == Direction.UP && request.getFloor() > elevatorController.getElevatorCar().getCurrentFloor()) ||
                (elevatorController.getElevatorCar().getDir() == Direction.DOWN && request.getFloor() < elevatorController.getElevatorCar().getCurrentFloor());

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
}
