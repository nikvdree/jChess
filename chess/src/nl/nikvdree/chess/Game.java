package nl.nikvdree.chess;

import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Move;
import nl.nikvdree.chess.bot.RandomChessBot;

import java.util.List;

public class Game {

    private Board board;
    private List<Move> moves;
    private RandomChessBot white;
    private RandomChessBot black;


    public Game() {
        board = new Board().build();

    }

    public Board getBoard() {
        return board;
    }
}
