package nl.nikvdree.chess.piece;

import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Location;

import java.util.List;

public class Bishop extends LinePiece {

    private static final Location[] offsets = {
            new Location(1, 1), new Location(-1, 1),
            new Location(1, -1), new Location(-1, -1),
    };

    public static List<Location> moves(int x, int y) {
       return moves(x, y, offsets);
    }

    public static List<Location> legalMoves(Board board, int x, int y){
        return legalMoves(board, x, y, offsets);
    }
}
