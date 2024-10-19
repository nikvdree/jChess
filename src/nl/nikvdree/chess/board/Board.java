package nl.nikvdree.chess.board;

import java.util.ArrayList;
import java.util.List;

import nl.nikvdree.chess.piece.*;
import static nl.nikvdree.chess.piece.Piece.*;

public class Board {

    private byte[][] board;
    private List<Location> black_pieces;
    private List<Location> white_pieces;

    private Location whiteKing;
    private Location blackKing;

    public Board(){
        board = new byte[8][8];
        initPieces();
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
        initPieces();
        return this;
    }

    private void initPieces(){
        black_pieces = new ArrayList<>();
        white_pieces = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                byte piece = board[row][col];
                if (color(piece) == WHITE) {
                    white_pieces.add(new Location(row, col));
                    if (piece == WHITE_KING) {
                        whiteKing = new Location(row, col);
                    }
                }
                else if (color(piece) == BLACK) {
                    black_pieces.add(new Location(row, col));
                    if (piece == BLACK_KING) {
                        blackKing = new Location(row, col);
                    }
                }
            }
        }
    }

    public List<Location> legalMovesFor(Location location){
        List<Location> moves = new ArrayList<>();
        byte piece = board[location.x()][location.y()];
        switch (piece) {
            case WHITE_PAWN, BLACK_PAWN -> moves = Pawn.legalMoves(this, location.x(), location.y());
            case WHITE_KNIGHT, BLACK_KNIGHT -> moves = Knight.legalMovesFor(this, location.x(), location.y());
            case WHITE_BISHOP, BLACK_BISHOP -> moves = Bishop.legalMoves(this, location.x(), location.y());
            case WHITE_ROOK, BLACK_ROOK -> moves = Rook.legalMoves(this, location.x(), location.y());
            case WHITE_QUEEN, BLACK_QUEEN -> moves = Queen.legalMoves(this, location.x(), location.y());
            case WHITE_KING, BLACK_KING -> moves = King.legalMoves(this, location.x(), location.y());
        }
        return moves;
    }

    public List<Move> legalMovesFor(byte color) {
        List<Move> allPossibleMoves = new ArrayList<>();
        List<Move> legalMoves = new ArrayList<>();

        for (Location location : color == WHITE ? white_pieces : black_pieces) {
            for (Location to : legalMovesFor(location)) {
                allPossibleMoves.add(new Move(board[location.x()][location.y()], location, to));
            }
        }

        for (Move move : allPossibleMoves) {
            Board copyBoard = this.copy();

            copyBoard.move(move);

            if (!copyBoard.check(color)) {
                legalMoves.add(move);
            }
        }

        return legalMoves;
    }


    public byte[][] getBoard() {
        return board;
    }

    public byte pos(int x, int y){
        if (x < 0 || x >= 8 || y < 0 || y >= 8)
            return -1;
        return board[x][y];
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

    public static boolean inBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
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

        if (color(piece) == WHITE) {
            if (piece == WHITE_KING) {
                whiteKing = location;
            }
            white_pieces.add(location);
        }
        else if (color(piece) == BLACK) {
            black_pieces.add(location);
            if (piece == BLACK_KING) {
                blackKing = location;
            }
        }
        board[location.x()][location.y()] = piece;
    }

    public void move(Location from, Location to){
        if (oppositeColor(from, to)){
            if (color(board[from.x()][from.y()]) == WHITE)
                black_pieces.remove(to);
            else if (color(board[from.x()][from.y()]) == BLACK)
                white_pieces.remove(to);
        }
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

    public byte get(Location location) {
        return board[location.x()][location.y()];
    }

    public boolean isOccupied(Location move) {
        return board[move.x()][move.y()] != EMPTY;
    }

    public boolean oppositeColor(Location from, Location to) {
        return color(board[from.x()][from.y()]) != color(board[to.x()][to.y()]);
    }

    public void move(Move move) {
        Location from = move.getFrom();
        Location to = move.getTo();
        byte piece = board[from.x()][from.y()];

        if (piece == WHITE_KING) {
            whiteKing = to;
        } else if (piece == BLACK_KING) {
            blackKing = to;
        }

        move(from, to);
    }

    public boolean check(byte color) {
        Location king = color == WHITE ? whiteKing : blackKing;
        if (king == null) {
            throw new IllegalStateException("King not found on the board");
        }

        List<Location> opponentPieces = color == WHITE ? black_pieces : white_pieces;
        for (Location opponentLocation : opponentPieces) {
            List<Location> opponentMoves = legalMovesFor(opponentLocation);
            if (opponentMoves.contains(king)) {
                return true;
            }
        }

        return false;
    }



    public boolean checkmate(byte color) {
        if (!check(color)) {
            return false;
        }

        List<Move> legalMoves = legalMovesFor(color);

        return legalMoves.isEmpty();
    }

    public boolean stalemate(){ //TODO
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != EMPTY) {
                    if (board[row][col] != WHITE_KING || board[row][col] != BLACK_KING) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public Board copy() {
        Board newBoard = new Board();
        newBoard.board = new byte[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(this.board[i], 0, newBoard.board[i], 0, 8);
        }

        newBoard.white_pieces = new ArrayList<>();
        for (Location loc : this.white_pieces) {
            if (newBoard.get(loc) == WHITE_KING){
                newBoard.whiteKing = new Location(loc.x(), loc.y());
            }
            newBoard.white_pieces.add(new Location(loc.x(), loc.y()));
        }

        newBoard.black_pieces = new ArrayList<>();
        for (Location loc : this.black_pieces) {
            if (newBoard.get(loc) == BLACK_KING){
                newBoard.blackKing = new Location(loc.x(), loc.y());
            }
            newBoard.black_pieces.add(new Location(loc.x(), loc.y()));
        }

        return newBoard;
    }


    public static void main(String[] args) {
        Board board = new Board();
        board.add(WHITE_ROOK, Location.of(4, 4));
        board.getBoard()[5][4] = WHITE_ROOK;
        board.getBoard()[3][4] = BLACK_ROOK;
        board.getBoard()[3][3] = BLACK_PAWN;
        board.printMoves(4, 4);
        board.getBoard()[4][4] = WHITE_BISHOP;
        board.printMoves(4, 4);
        board.getBoard()[4][4] = WHITE_QUEEN;
        board.printMoves(4, 4);
        board.getBoard()[4][4] = WHITE_KING;
        board.printMoves(4, 4);
        board.getBoard()[4][4] = WHITE_PAWN;
        board.printMoves(4, 4);
        board.getBoard()[4][4] = BLACK_PAWN;
        board.printMoves(4, 4);
        board.getBoard()[4][4] = BLACK_KNIGHT;
        board.printMoves(4, 4);
        board = new Board().build();
        board.print();
        board.printMoves(1, 4);
        board.add(BLACK_PAWN, new Location(2, 5));
        board.add(BLACK_PAWN, new Location(2, 4));
        board.printMoves(1, 4);
    }

    public List<Location> getBlackPieces() {
        return black_pieces;
    }

    public List<Location> getWhitePieces() {
        return white_pieces;
    }
}
