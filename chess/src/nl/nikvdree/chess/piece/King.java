package nl.nikvdree.chess.piece;

import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Location;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public static List<Location> moves(int x, int y) {
        return List.of(
                new Location(x+1, y), new Location(x, y+1),
                new Location(x-1, y), new Location(x, y-1),
                new Location(x+1, y+1), new Location(x-1, y+1),
                new Location(x+1, y-1), new Location(x-1, y-1)
        );
    }

    public static List<Location> legalMoves(Board board, int x, int y){
        List<Location> moves = moves(x, y);
        List<Location> legalMoves = new ArrayList<>();
        for (Location move : moves) {
            if (move.isValid() && !board.isOccupied(move)) {
                legalMoves.add(move);
            }
            else if (move.isValid() && board.isOccupied(move) && !Piece.compareColor(board.getBoard()[x][y], board.getBoard()[move.x()][move.y()])) {
                legalMoves.add(move);
            }
        }
        return legalMoves;
    }
}
