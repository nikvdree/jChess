package nl.nikvdree.chess.bot;

import nl.nikvdree.chess.game.Game;
import nl.nikvdree.chess.board.Board;
import nl.nikvdree.chess.board.Move;

import java.util.List;
import java.util.Random;

public class RandomChessBot {

    private Board board;
    private byte color;
    private Random random;

    public RandomChessBot(Game game, byte color){
        board = game.getBoard();
        this.color = color;
        this.random = new Random();
    }

    public Move getNextMove(){
        List<Move> moves = board.legalMovesFor(color);
        int rand = random.nextInt(moves.toArray().length);
        return moves.get(rand);
    }
}
