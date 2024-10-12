import nl.nikvdree.chess.board.Location;
import nl.nikvdree.chess.piece.Bishop;

import java.util.List;

public class BishopTest {

    public static void main(String[] args) {
        Bishop.moves(5, 5).forEach(System.out::println);
        System.out.println();
        List<Location> list = Bishop.moves(4, 4);
        list.forEach(System.out::println);
        System.out.println(list.size());// FROM 4,4: 3,3; 2,2; 1,1; 0,0; 5,5; 6,6; 7,7; 3,5; 2,6; 1,7; 5,3; 6,2; 7,1
        System.out.println();
        Bishop.moves(0, 0).forEach(System.out::println);
        System.out.println();
        Bishop.moves(7, 7).forEach(System.out::println);
    }
}
