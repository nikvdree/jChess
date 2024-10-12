package nl.nikvdree.chess.piece;

import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Location;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public static List<Location> legalMovesFor(Board board, int x, int y) {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(x + 2, y + 1));
        locations.add(new Location(x + 2, y - 1));
        locations.add(new Location(x - 2, y + 1));
        locations.add(new Location(x - 2, y - 1));
        locations.add(new Location(x + 1, y + 2));
        locations.add(new Location(x + 1, y - 2));
        locations.add(new Location(x - 1, y + 2));
        locations.add(new Location(x - 1, y - 2));
        for (int i = locations.size() - 1; i >= 0; i--) {
            Location location = locations.get(i);
            if (!location.isValid()) {
                locations.remove(i);
            }
            else if (board.get(locations.get(i)) != EMPTY) {
                if (!compareColor(board.getBoard()[x][y], board.getBoard()[location.x()][location.y()])) {
                    locations.add(location);
                }
                locations.remove(i);
            }
        }
        return locations;
    }
}
