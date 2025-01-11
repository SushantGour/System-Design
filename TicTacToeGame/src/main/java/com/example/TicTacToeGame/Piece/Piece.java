package com.example.TicTacToeGame.Piece;

import com.example.TicTacToeGame.Constants.PieceType;
import lombok.Data;

@Data
public class Piece {
    PieceType type;

    public Piece(PieceType type){
        this.type=type;
    }
}
