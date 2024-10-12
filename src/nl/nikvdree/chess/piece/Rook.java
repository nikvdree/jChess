package nl.nikvdree.chess.piece;

import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Location;

import java.util.List;

public class Rook extends LinePiece {

    private static final Location[] offsets = {
            new Location(1, 0), new Location(0, 1),
            new Location(-1, 0), new Location(0, -1),
    };

    public static List<Location> moves(int x, int y) {
        return moves(x, y, offsets);
    }

    public static List<Location> legalMoves(Board board, int x, int y){
        return legalMoves(board, x, y, offsets);
    }
}
