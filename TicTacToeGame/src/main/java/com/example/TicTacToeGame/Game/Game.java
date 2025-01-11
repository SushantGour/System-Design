package com.example.TicTacToeGame.Game;

import com.example.TicTacToeGame.Board.Board;
import com.example.TicTacToeGame.Constants.PieceType;
import com.example.TicTacToeGame.Player.Player;
import com.example.TicTacToeGame.model.Pair;

import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Game {
    Deque<Player> players;
    Board board;

    public Game(Deque<Player> players, Board board) {
        this.players = players;
        this.board = board;
    }

    public String startGame(){
        // There can only be two results of this game : win or tie

        // flag to check if the game is tied or not : if it is true, then it means that
        // the game is still in process and if it is false, then it means that the game is tied
        boolean flag = true;

        while(flag){

            // take out the player who's turn is this
            Player currentPlayer = players.removeFirst();

            //print the board's current state
            board.printBoard();

            // Check if there are any free spaces or not : if yes, then continue the game, else,
            // stop the game as there is no winner.
            List<Pair> freeSpaces =  board.getFreeCells();
            if(freeSpaces.isEmpty()) {
                flag = false;
                continue;
            }

            // take input from user for his/her next move
            System.out.print("Player:" + currentPlayer. getName()+ " Enter row,column: ");
            Scanner inputScanner = new Scanner(System.in);
            String s = inputScanner.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn = Integer.valueOf(values[1]);


            //place the piece
            boolean pieceAddedSuccessfully = board.addPiece(inputRow,inputColumn, currentPlayer.getPiece());
            if(!pieceAddedSuccessfully) {
                // invalid location, try again
                System.out.println("Invalid position, try again");
                // add the current player back to the front of the deque so that he/she can try again
                players.addFirst(currentPlayer);
                continue;
            }

            // add current player to the end of the deque
            players.addLast(currentPlayer);

            boolean winner = isThereWinner(inputRow, inputColumn, currentPlayer.getPiece().getType());
            if(winner) {
                // we return the current player's name if the current player's move results in a win
                return currentPlayer.getName();
            }
        }

        // return tie in case of a tie
        return "tie";
    }

    public boolean isThereWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch = true;
        boolean columnMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //need to check in row
        for(int j=0;j<board.getN();j++) {
            if(board.getBoard()[row][j] == null || board.getBoard()[row][j].getType() != pieceType) {
                rowMatch = false;
            }
        }

        //need to check in column
        for(int i=0;i<board.getN();i++) {
            if(board.getBoard()[i][column] == null || board.getBoard()[i][column].getType() != pieceType) {
                columnMatch = false;
            }
        }

        //need to check the two diagonals of the square
        for(int i=0, j=0; i<board.getN();i++,j++) {
            if (board.getBoard()[i][j] == null || board.getBoard()[i][j].getType() != pieceType) {
                diagonalMatch = false;
            }
        }

        //need to check anti-diagonals
        for(int i=0, j=board.getN()-1; i<board.getN() && j>=0 ;i++,j--) {
            if (board.getBoard()[i][j] == null || board.getBoard()[i][j].getType() != pieceType) {
                antiDiagonalMatch = false;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;
    }

}
