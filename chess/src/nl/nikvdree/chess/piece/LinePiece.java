package nl.nikvdree.chess.piece;

import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Location;

import java.util.ArrayList;
import java.util.List;

abstract class LinePiece extends Piece {

    static List<Location> moves(int x, int y, Location[] offsets) {
        List<Location> locations = new ArrayList<>();
        // move in each direction for each offset
        // add location till wall is hit
        for (Location offset : offsets) {
            for (byte i = START; i < LIMIT; i++) {
                int loc_x = x + (i * offset.x());
                int loc_y = y + (i * offset.y());
                if (loc_x < START || loc_x >= LIMIT){
                    continue;
                }
                if (loc_y < START
                        || loc_y >= LIMIT){
                    continue;
                }
                if (x == loc_x && y == loc_y){
                    continue;
                }
                locations.add(new Location(loc_x, loc_y));
            }
        }
        return locations;
    }

    static List<Location> legalMoves(Board board, int x, int y, Location[] offsets) {
        List<Location> locations = new ArrayList<>();
        // move in each direction for each offset
        // add location till wall is hit
        for (Location offset : offsets) {
            for (byte i = START; i < LIMIT; i++) {
                int loc_x = x + (i * offset.x());
                int loc_y = y + (i * offset.y());
                if (loc_x < START || loc_x >= LIMIT){
                    continue;
                }
                if (loc_y < START
                        || loc_y >= LIMIT){
                    continue;
                }
                if (x == loc_x && y == loc_y){
                    continue;
                }
                if (board.getBoard()[loc_x][loc_y] != EMPTY){
                    if (!compareColor(board.getBoard()[x][y], board.getBoard()[loc_x][loc_y])) {
                        locations.add(new Location(loc_x, loc_y));
                    }
                    break;
                }
                locations.add(new Location(loc_x, loc_y));
            }
        }
        return locations;
    }
}
