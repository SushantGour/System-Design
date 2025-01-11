package com.example.TicTacToeGame.Piece.PieceFactory;

import com.example.TicTacToeGame.Piece.Piece;
import com.example.TicTacToeGame.Constants.PieceType;

public interface PieceFactory {
    public Piece createPiece(PieceType type);
}
