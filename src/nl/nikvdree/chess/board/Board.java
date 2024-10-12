package nl.nikvdree.chess.board;

import java.util.ArrayList;
import java.util.List;

import nl.nikvdree.chess.piece.*;
import static nl.nikvdree.chess.piece.Piece.*;

public class Board {

    private byte[][] board;
    private List<Location> black_pieces;
    private List<Location> white_pieces;

    public Board(){
        board = new byte[8][8];
    }

    public Board build(){
        return initBoard();
    }

    private Board initBoard(){
        board = new byte[][]{
                {WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK},
                {WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
                {BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN},
                {BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK},
        };
        init_pieces();
        return this;
    }

    private void init_pieces(){
        black_pieces = new ArrayList<>();
        white_pieces = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (color(board[row][col]) == WHITE) {
                    white_pieces.add(new Location(row, col));
                }
                else if (color(board[row][col]) == BLACK) {
                    black_pieces.add(new Location(row, col));
                }
            }
        }
    }

    public List<Location> legalMovesFor(Location location){
        List<Location> moves = new ArrayList<>();
        byte piece = board[location.x()][location.y()];
        switch (piece) {
            case WHITE_PAWN, BLACK_PAWN -> moves = Pawn.legalMoves(this, location.x(), location.y());
            case WHITE_BISHOP, BLACK_BISHOP -> moves = Bishop.legalMoves(this, location.x(), location.y());
            case WHITE_ROOK, BLACK_ROOK -> moves = Rook.legalMoves(this, location.x(), location.y());
            case WHITE_QUEEN, BLACK_QUEEN -> moves = Queen.legalMoves(this, location.x(), location.y());
            case WHITE_KING, BLACK_KING -> moves = King.legalMoves(this, location.x(), location.y());
        }
        return moves;
    }

    public List<Move> legalMovesFor(byte color){
        List<Move> moves = new ArrayList<>();
        for (Location location : color == WHITE ? white_pieces : black_pieces) {
            for (Location to : legalMovesFor(location)) {
                moves.add(new Move(board[location.x()][location.y()], location, to));
            }
        }
        return moves;
    }

    public byte[][] getBoard() {
        return board;
    }

    public void print(){
        System.out.println("================");
        for (byte[] row : board) {
            for (byte piece : row) {
                System.out.print(symbol(piece) + " ");
            }
            System.out.println();
        }
        System.out.println("================");
    }

    public void printMoves(int x, int y) {
        List<Location> moves = legalMovesFor(new Location(x, y));
        System.out.println("================");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                boolean isMove = false;
                if (row == x && col == y) {
                    System.out.print(symbol(board[row][col]) + "}");
                    continue;
                }
                // Check if current position is a legal move
                for (Location move : moves) {
                    if (move.x() == row && move.y() == col) { // Correct comparison of move coordinates
                        isMove = true;
                        if (board[row][col] != EMPTY)
                        {
                            System.out.print(symbol(board[row][col]) + "]");
                            break;
                        }
                        System.out.print("x ");
                        break;
                    }
                }
                // If no legal move, print the symbol at that position
                if (!isMove) {
                    System.out.print(symbol(board[row][col]) + " ");
                }
            }
            System.out.println();
        }
        System.out.println("================");
    }

    public void printMoves(Location location) {
        printMoves(location.x(), location.y());
    }

    public void add(byte piece, Location location){
        if (board[location.x()][location.y()] != EMPTY)
            throw new IllegalArgumentException("Location is already occupied");

        if (color(piece) == WHITE)
            white_pieces.add(location);
        else if (color(piece) == BLACK)
            black_pieces.add(location);
        board[location.x()][location.y()] = piece;
    }

    public void move(Location from, Location to){
           board[to.x()][to.y()] = board[from.x()][from.y()];
           board[from.x()][from.y()] = EMPTY;
              if (color(board[to.x()][to.y()]) == WHITE) {
                white_pieces.remove(from);
                white_pieces.add(to);
              }
              else if (color(board[to.x()][to.y()]) == BLACK) {
                black_pieces.remove(from);
                black_pieces.add(to);
              }
    }

    public static void main(String[] args) {
        Board board = new Board();
        board.getBoard()[4][4] = WHITE_ROOK;
        board.getBoard()[5][4] = WHITE_ROOK;
        board.getBoard()[3][4] = BLACK_ROOK;
        board.getBoard()[3][3] = BLACK_PAWN;
        board.printMoves(4,4);
        board.getBoard()[4][4] = WHITE_BISHOP;
        board.printMoves(4,4);
        board.getBoard()[4][4] = WHITE_QUEEN;
        board.printMoves(4,4);
        board.getBoard()[4][4] = WHITE_KING;
        board.printMoves(4,4);
        board.getBoard()[4][4] = WHITE_PAWN;
        board.printMoves(4,4);
        board.getBoard()[4][4] = BLACK_PAWN;
        board.printMoves(4,4);
        board = new Board().build();
        board.print();
        board.printMoves(1,4);
        board.add(BLACK_PAWN, new Location(2, 5));
        board.add(BLACK_PAWN, new Location(2, 4));
        board.printMoves(1,4);
    }

    public boolean isOccupied(Location move) {
        return board[move.x()][move.y()] != EMPTY;
    }
}
