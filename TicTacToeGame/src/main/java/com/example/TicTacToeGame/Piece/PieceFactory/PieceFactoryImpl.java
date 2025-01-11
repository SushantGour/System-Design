package com.example.TicTacToeGame.Piece.PieceFactory;

import com.example.TicTacToeGame.Piece.Piece;
import com.example.TicTacToeGame.Piece.PieceO;
import com.example.TicTacToeGame.Piece.PieceX;
import com.example.TicTacToeGame.Constants.PieceType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PieceFactoryImpl implements PieceFactory{

    @Override
    public Piece createPiece(PieceType type) {
        if(type.equals(PieceType.O)){
            return new PieceO();
        }
        else if(type.equals(PieceType.X)){
            return new PieceX();
        }

        log.error("Invalid piece type");
        return null;
    }
}
