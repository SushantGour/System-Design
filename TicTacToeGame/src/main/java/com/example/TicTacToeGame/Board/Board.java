package com.example.TicTacToeGame.Board;

import com.example.TicTacToeGame.Piece.Piece;
import com.example.TicTacToeGame.model.Pair;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
public class Board {
    // we are taking a square board of size n*n
    int n;
    Piece[][] board;

    public Board(int n){
        this.n = n;
        board = new Piece[n][n];
    }

    public boolean addPiece(int i, int j, Piece piece){
        if(board[i][j]!=null){
            return false;
        }
        board[i][j]=piece;
        return true;
    }

    public List<Pair> getFreeCells() {
        List<Pair> freeCells = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == null) {
                    Pair pair = new Pair(i,j);
                    freeCells.add(pair);
                }
            }
        }
        return freeCells;
    }

    public void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != null) {
                    // Print the current cell's PieceType enum's name
                    System.out.print(board[i][j].getType().name() + "   ");
                } else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }



}
