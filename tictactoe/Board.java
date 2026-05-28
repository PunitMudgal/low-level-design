package tictactoe;

public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public boolean placePiece(int row, int col, PlayingPiece piece) {
        if (board[row][col] != null) {
            return false;
        }
        board[row][col] = piece;
        return true;
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != null) {
                    System.out.println(" " + board[i][j].pieceType.name() + " ");
                } else
                    System.out.println("   ");
                if (j < size - 1)
                    System.out.println("|");
            }
            System.out.println();
            if (i < size - 1)
                System.out.println("---+---+---");
        }
        System.out.println();

    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == null)
                    return false;
            }
        }
        return true;
    }

}
