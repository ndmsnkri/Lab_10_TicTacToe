import java.util.Scanner;

//Initialize the board and set the current player to X
//clearBoard()
//        currentPlayer = "X"
//
// Start the game loop
//        gameOver = false
//        while (not gameOver):
//      Display the current board
//        showBoard()
//
//      Get the player's move (row and column)
//        playerMove = getPlayerMove()
//
//      Convert the player's move to array indices (0-2)
//        row = playerMove.row - 1
//        col = playerMove.col - 1
//
//      Check if the move is valid
//        if (isValidMove(row, col)):
//      Update the board with the player's move
//        board[row][col] = currentPlayer
//
//      Check for a win
//        if (isWin(currentPlayer)):
//        announceWinner(currentPlayer)
//        gameOver = true
//      Check for a tie
//        else if (isTie()):
//        announceTie()
//        gameOver = true
//        else:
//      Toggle the player for the next turn
//        togglePlayer()
//        else:
//      Invalid move, ask the player to try again
//        print("Invalid move. Please try again.")
//
// End of game loop

public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args) {
        clearBoard();
        showBoard();

        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            int[] move = getPlayerMove(scanner);
            int row = move[0];
            int col = move[1];

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                showBoard();
                if (isWin(currentPlayer)) {
                    System.out.println(currentPlayer + " wins!");
                    gameOver = true;
                } else if (isTie()) {
                    System.out.println("It's a tie!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }

        scanner.close();
    }

    private static void clearBoard()
    {
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }
    }

    private static void showBoard() {
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                System.out.print(board[row][col]);
                if (col < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
        }
    }

    private static int[] getPlayerMove(Scanner scanner) {
        System.out.print("\nPlayer " + currentPlayer + ", enter your move (row and column): ");
        System.out.println();
        int row = SafeInput.getRangedInt(scanner, "Enter the row", 1, 3) - 1;
        int col = SafeInput.getRangedInt(scanner, "Enter the column", 1, 3) - 1;
        System.out.println();
        return new int[]{row, col};
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagonalWin(player))
        {
            return true;
        }
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROW; row++) {
            if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < COL; col++) {
            if (board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        if (isWin("X") || isWin("O")) {
            return false; // If there's a win, it's not a tie
        }

        int moveCount = 0;

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (!board[i][j].equals(" ")) {
                    moveCount++;
                }
            }
        }

        if (moveCount >= 9) {
            return true; // Full board tie
        }

        return false; // If there are open cells and no win, it's a tie
    }

}
