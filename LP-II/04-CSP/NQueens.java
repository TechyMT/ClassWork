import java.util.Scanner;

public class NQueens {

    // Function to check if it's safe to place a queen at board[row][col]
    static boolean isSafe(int row, int col, int[][] board, int n) {
        // Check row and column attacks
        for (int i = 0; i < n; i++) {
            if (board[row][i] == 1 || board[i][col] == 1) return false;
        }

        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // Bottom left diagonal
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }

        // Top right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) return false;
        }

        // Bottom right diagonal
        for (int i = row, j = col; i < n && j < n; i++, j++) {
            if (board[i][j] == 1) return false;
        }

        return true;
    }

    // Function to solve N-Queens problem using backtracking
    static boolean solveNQueens(int[][] board, int col, int n) {
        if (col >= n) {
            return true;
        }

        for (int i = 0; i < n; i++) {
            // Check if placing the queen is safe
            if (isSafe(i, col, board, n)) {
                board[i][col] = 1;

                // Recur for the next queen
                if (solveNQueens(board, col + 1, n)) {
                    return true;
                }

                board[i][col] = 0;
            }
        }
        return false;
    }

    static void printSolution(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the board size (n): ");
        int n = scanner.nextInt();

        int[][] board = new int[n][n];

        if (solveNQueens(board, 0, n)) {
            System.out.println("Solution exists:");
            printSolution(board, n);
        } else {
            System.out.println("No solution exists");
        }
    }
}
