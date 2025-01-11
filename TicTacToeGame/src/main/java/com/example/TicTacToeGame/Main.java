package com.example.TicTacToeGame;

import com.example.TicTacToeGame.Board.Board;
import com.example.TicTacToeGame.Constants.PieceType;
import com.example.TicTacToeGame.Game.Game;
import com.example.TicTacToeGame.Piece.PieceFactory.PieceFactory;
import com.example.TicTacToeGame.Piece.PieceFactory.PieceFactoryImpl;
import com.example.TicTacToeGame.Player.Player;
import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.LinkedList;

@Slf4j
public class Main {

    public static void main(String[] args){
        PieceFactory pieceFactory = new PieceFactoryImpl();

        // create two players
        Deque<Player> players = new LinkedList<>();
        Player player1 = new Player("player1", pieceFactory.createPiece(PieceType.O));
        Player player2 = new Player("player2", pieceFactory.createPiece(PieceType.X));
        players.add(player1);
        players.add(player2);

        Board board = new Board(3);

        Game game = new Game(players,board);

        String winner = game.startGame();

        log.info("The winner of the game is : {}", winner);
    }
}
