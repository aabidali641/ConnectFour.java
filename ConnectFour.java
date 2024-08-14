import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';

    private char[][] board = new char[ROWS][COLS];

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        game.play();
    }

    public ConnectFour() {
        // Initialize the board with empty slots
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void play() {
        boolean playerOneTurn = true;
        boolean gameWon = false;

        while (!gameWon && !isBoardFull()) {
            printBoard();
            int col = getPlayerMove(playerOneTurn);
            if (dropDisc(col, playerOneTurn ? PLAYER_ONE : PLAYER_TWO)) {
                if (checkWin()) {
                    gameWon = true;
                    printBoard();
                    System.out.println("Player " + (playerOneTurn ? "One" : "Two") + " wins!");
                } else {
                    playerOneTurn = !playerOneTurn;
                }
            } else {
                System.out.println("Column full! Choose another column.");
            }
        }

        if (!gameWon) {
            System.out.println("It's a draw!");
        }
    }

    private void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int getPlayerMove(boolean playerOneTurn) {
        Scanner scanner = new Scanner(System.in);
        int col;
        do {
            System.out.println("Player " + (playerOneTurn ? "One (X)" : "Two (O)") + ", choose a column (1-7): ");
            col = scanner.nextInt() - 1;
        } while (col < 0 || col >= COLS);
        return col;
    }

    private boolean dropDisc(int col, char disc) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY) {
                board[i][col] = disc;
                return true;
            }
        }
        return false;
    }

    private boolean checkWin() {
        // Check horizontal, vertical, and diagonal directions for a win
        return checkHorizontalWin() || checkVerticalWin() || checkDiagonalWin();
    }

    private boolean checkHorizontalWin() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] != EMPTY && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2] && board[i][j] == board[i][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVerticalWin() {
        for (int i = 0; i <= ROWS - 4; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] != EMPTY && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j] && board[i][j] == board[i + 3][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        // Check for diagonal win (bottom-left to top-right)
        for (int i = 3; i < ROWS; i++) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] != EMPTY && board[i][j] == board[i - 1][j + 1] && board[i][j] == board[i - 2][j + 2] && board[i][j] == board[i - 3][j + 3]) {
                    return true;
                }
            }
        }
        // Check for diagonal win (top-left to bottom-right)
        for (int i = 0; i <= ROWS - 4; i++) {
            for (int j = 0; j <= COLS - 4; j++) {
                if (board[i][j] != EMPTY && board[i][j] == board[i + 1][j + 1] && board[i][j] == board[i + 2][j + 2] && board[i][j] == board[i + 3][j + 3]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int j = 0; j < COLS; j++) {
            if (board[0][j] == EMPTY) {
                return false;
            }
        }
        return true;
    }
}
