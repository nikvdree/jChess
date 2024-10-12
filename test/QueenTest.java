import nl.nikvdree.chess.board.Location;
import nl.nikvdree.chess.piece.Queen;

import java.util.List;

public class QueenTest {
    public static void main(String[] args) {
        Queen.moves(5, 5).forEach(System.out::println);
        System.out.println();
        List<Location> list = Queen.moves(4, 4);
        list.forEach(System.out::println);
        System.out.println(list.size());
        System.out.println(list.size() == 27);
        System.out.println();
        Queen.moves(0, 0).forEach(System.out::println);
        System.out.println();
        Queen.moves(7, 7).forEach(System.out::println);
    }
}
