package nl.nikvdree.chess.game;

import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Move;
import nl.nikvdree.chess.bot.RandomChessBot;

import java.util.List;

import static nl.nikvdree.chess.piece.Piece.*;

public class Game {

    private Board board;
    private List<Move> moves;
    private RandomChessBot white;
    private RandomChessBot black;

    private byte turn = WHITE;
    private int turnCount = 0;


    public Game() {
        board = new Board().build();
        white = new RandomChessBot(this, WHITE);
        black = new RandomChessBot(this, BLACK);
    }

    public void start(){
        board.print();
        int i = 0;
        while (true){
            if (i == 50){
                System.out.println("Game over!");
                break;
            }
            i++;
            System.out.println("Turn " + turnCount + ": " + (turn == WHITE ? "White" : "Black"));

            if (board.stalemate()){
                System.out.println("Stalemate");
                break;
            }

            if (board.checkmate(turn)){
                System.out.println("Checkmate! " + (turn == WHITE ? "Black" : "White") + " wins.");
                break;
            }

            if (board.check(turn)){
                System.out.println("Check for " + (turn == WHITE ? "White" : "Black"));
            }

            moves = board.legalMovesFor(turn);

            if (moves.isEmpty()){
                if (board.check(turn)) {
                    System.out.println("Checkmate! " + (turn == WHITE ? "Black" : "White") + " wins.");
                } else {
                    System.out.println("Stalemate! It's a draw.");
                }
                break;
            }

            Move move = turn == WHITE ? white.getNextMove() : black.getNextMove();

            if (!moves.contains(move)) {
                System.out.println("Invalid move attempted by " + (turn == WHITE ? "White" : "Black"));
                System.out.println("Move: " + move);
                System.out.println("Available legal moves: " + moves);
                System.out.println("Game terminated due to invalid move.");
                break;
            }

            board.move(move);
            System.out.println((turn == WHITE ? "White" : "Black") + " moves: " + move);
            board.print();

            turn = turn == WHITE ? BLACK : WHITE;
            turnCount++;
        }

        System.out.println("Final Pieces:");
        System.out.println("Black: " + board.getBlackPieces());
        System.out.println("White: " + board.getWhitePieces());
        System.out.println("Black Legal Moves: " + board.legalMovesFor(BLACK).size());
        System.out.println("White Legal Moves: " + board.legalMovesFor(WHITE).size());
    }

    public Board getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
