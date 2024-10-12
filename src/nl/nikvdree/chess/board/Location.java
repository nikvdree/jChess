package nl.nikvdree.chess.board;

public record Location(int x, int y) implements Comparable<Location> {

    @Override
    public int compareTo(Location location) {
        return this.x() - location.x() + this.y() - location.y();
    }

    public boolean isValid() {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public static Location of(int x, int y){
        return new Location(x, y);
    }
}
