package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;

// Game HAS-A Board and HAS-A List<Player>
public class Game {

    private List<Player> players; // HAS-A relationship
    private Board board; // HAS-A relationship

    public Game() {
        initGame();
    }

    // Set up players and board before game starts
    private void initGame() {
        players = new ArrayList<>();

        // Player 1 gets PieceX, Player 2 gets PieceO
        Player player1 = new Player("Player 1", new PieceX());
        Player player2 = new Player("Player 2", new PieceO());

        players.add(player1);
        players.add(player2);

        // Standard 3x3 board
        board = new Board(3);
    }

    // Core game loop — returns the name of winner, or "Draw"
    public String startGame() {
        Scanner scanner = new Scanner(System.in);
        int currentPlayerIndex = 0;

        System.out.println("=== Tic-Tac-Toe ===");
        System.out.println("Player 1 → X  |  Player 2 → O");
        board.printBoard();

        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getPiece().pieceType + ")");

            // Get valid move from player
            int row = -1, col = -1;
            boolean validMove = false;

            while (!validMove) {
                try {
                    System.out.print("Enter row (0-2): ");
                    row = scanner.nextInt();
                    System.out.print("Enter col (0-2): ");
                    col = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter numbers only.");
                    scanner.nextLine(); // clear invalid token
                    continue;
                }

                if (row < 0 || row >= board.size || col < 0 || col >= board.size) {
                    System.out.println("Out of bounds! Try again.");
                } else {
                    validMove = board.placePiece(row, col, currentPlayer.getPiece());
                    if (!validMove)
                        System.out.println("Cell already taken! Try again.");
                }
            }

            board.printBoard();

            // Check win condition after every move
            if (isWinner(row, col, currentPlayer.getPiece().pieceType)) {
                return currentPlayer.getName() + " wins!";
            }

            // Check draw
            if (board.isBoardFull()) {
                return "It's a Draw!";
            }

            // Switch turns (toggle between 0 and 1)
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    // Check if the last move caused a win — only check row, col, and diagonals of
    // last move
    private boolean isWinner(int lastRow, int lastCol, PieceType pieceType) {
        // Check entire row
        boolean rowWin = true;
        for (int j = 0; j < board.size; j++) {
            if (board.board[lastRow][j] == null || board.board[lastRow][j].pieceType != pieceType) {
                rowWin = false;
                break;
            }
        }
        if (rowWin)
            return true;

        // Check entire column
        boolean colWin = true;
        for (int i = 0; i < board.size; i++) {
            if (board.board[i][lastCol] == null || board.board[i][lastCol].pieceType != pieceType) {
                colWin = false;
                break;
            }
        }
        if (colWin)
            return true;

        // Check main diagonal (top-left to bottom-right)
        boolean diagWin = true;
        for (int i = 0; i < board.size; i++) {
            if (board.board[i][i] == null || board.board[i][i].pieceType != pieceType) {
                diagWin = false;
                break;
            }
        }
        if (diagWin)
            return true;

        // Check anti-diagonal (top-right to bottom-left)
        boolean antiDiagWin = true;
        for (int i = 0; i < board.size; i++) {
            int j = board.size - 1 - i;
            if (board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                antiDiagWin = false;
                break;
            }
        }
        return antiDiagWin;
    }
}
