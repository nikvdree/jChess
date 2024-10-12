package nl.nikvdree.chess.piece;

import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Location;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public static List<Location> moves(int x, int y, byte color){
        List<Location> moves = new ArrayList<>();
        if (color == WHITE) {
            moves.add(new Location(x + 1, y));
            moves.add(new Location(x + 1, y + 1));
            moves.add(new Location(x + 1, y - 1));
        }
        else if (color == BLACK) {
            moves.add(new Location(x - 1, y));
            moves.add(new Location(x - 1, y + 1));
            moves.add(new Location(x - 1, y - 1));
        }
        else {
            throw new IllegalArgumentException("Invalid color: " + color);
        }
        return moves;
    }

    public static List<Location> legalMoves(Board board, int x, int y) {
        List<Location> moves = new ArrayList<>();
        byte color = color(board.getBoard()[x][y]);
        if (color == WHITE) {
            if (board.getBoard()[x + 1][y] == EMPTY) {
                moves.add(new Location(x + 1, y));
                if (x == 1) {
                    if (board.getBoard()[x + 2][y] == EMPTY) {
                        moves.add(new Location(x + 2, y));
                    }
                }
            }
            if (color(board.getBoard()[x + 1][y + 1]) == BLACK) {
                moves.add(new Location(x + 1, y + 1));
            }
            if (color(board.getBoard()[x + 1][y - 1]) == BLACK) {
                moves.add(new Location(x + 1, y - 1));
            }
        }
        else if (color == BLACK) {
            if (board.getBoard()[x - 1][y] == EMPTY) {
                moves.add(new Location(x - 1, y));
                if (x == 6) {
                    if (board.getBoard()[x - 2][y] == EMPTY) {
                        moves.add(new Location(x - 2, y));
                    }
                }
            }
            if (color(board.getBoard()[x - 1][y + 1])  == WHITE) {
                moves.add(new Location(x - 1, y + 1));
            }
            if (color(board.getBoard()[x - 1][y - 1])  == WHITE) {
                moves.add(new Location(x - 1, y - 1));
            }
        }
        else {
            throw new IllegalArgumentException("Invalid color: " + color);
        }
        return moves;
    }
}
