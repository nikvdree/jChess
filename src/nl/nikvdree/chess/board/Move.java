package nl.nikvdree.chess.board;

import java.util.Objects;

public class Move {
    private byte piece;
    private Location from;
    private Location to;

    public Move(byte piece, Location from, Location to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    public byte getPiece() {
        return piece;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;
        return piece == move.piece &&
                Objects.equals(from, move.from) &&
                Objects.equals(to, move.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piece, from, to);
    }

    @Override
    public String toString() {
        return "Move{" +
                "piece=" + piece +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
