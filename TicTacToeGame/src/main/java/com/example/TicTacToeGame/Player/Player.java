package com.example.TicTacToeGame.Player;

import com.example.TicTacToeGame.Piece.Piece;
import lombok.Data;

@Data
public class Player {
    String name;
    Piece piece;

    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }
}
