package com.example.ElevatorSystem.strategies;

import com.example.ElevatorSystem.constants.Direction;
import lombok.Data;

@Data
public abstract class ElevatorSelectionStrategy {
    public abstract int selectElevator(int floor, Direction direction);
}
