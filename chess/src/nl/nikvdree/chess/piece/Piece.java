package nl.nikvdree.chess.piece;

public class Piece {

    public static final byte
            WHITE = 1,
            BLACK = 0,
            NONE = -1;

    public static byte color(byte piece) {
        if (piece < EMPTY || piece > BLACK_KING){
            throw new IllegalArgumentException("Piece: " + piece + " is not a valid color, should be between 0 and 12");
        }
        if (piece == EMPTY){
            return NONE;
        }
        return piece < 7 ? WHITE : BLACK;
    }

    public static final byte
            EMPTY = 0,

            WHITE_PAWN = 1,
            WHITE_KNIGHT = 2,
            WHITE_BISHOP = 3,
            WHITE_ROOK = 4,
            WHITE_QUEEN = 5,
            WHITE_KING = 6,

            BLACK_PAWN = 7,
            BLACK_KNIGHT = 8,
            BLACK_BISHOP = 9,
            BLACK_ROOK = 10,
            BLACK_QUEEN = 11,
            BLACK_KING = 12;

    public static String symbol(byte piece) {
        return switch (piece) {
            case WHITE_PAWN -> "P";
            case WHITE_KNIGHT -> "N";
            case WHITE_BISHOP -> "B";
            case WHITE_ROOK -> "R";
            case WHITE_QUEEN -> "Q";
            case WHITE_KING -> "K";
            case BLACK_PAWN -> "p";
            case BLACK_KNIGHT -> "n";
            case BLACK_BISHOP -> "b";
            case BLACK_ROOK -> "r";
            case BLACK_QUEEN -> "q";
            case BLACK_KING -> "k";
            case EMPTY -> ".";
            default -> "?";  // Unknown piece
        };
    }

    public static boolean compareColor(byte piece1, byte piece2) {
        return color(piece1) == color(piece2);
    }

    public static final int START = 0, LIMIT = 8;
}
