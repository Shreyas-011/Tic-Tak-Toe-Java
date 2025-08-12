import java.util.Scanner;

public class TicTacToe {
    static String[] board;
    static String turn;

    public static void main(String[] args) {
        board = new String[9];
        turn = "X";
        String winner = null;

        // Initialize the board with numbers 1-9
        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }

        printBoard();
        Scanner in = new Scanner(System.in);

        while (winner == null) {
            System.out.println(turn + "'s turn. Enter a slot number (1-9):");
            
            int slot;
            // Validate input
            try {
                slot = in.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                in.next(); // clear invalid input
                continue;
            }

            if (slot < 1 || slot > 9) {
                System.out.println("Invalid slot! Choose a number between 1 and 9.");
                continue;
            }

            // Check if the slot is already taken
            if (board[slot - 1].equals(String.valueOf(slot))) {
                board[slot - 1] = turn;
                winner = checkWinner();
                turn = turn.equals("X") ? "O" : "X";
            } else {
                System.out.println("Slot already taken. Try another one.");
            }

            printBoard();
        }

        in.close();

        // Declare the result
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Congratulations! " + winner + " wins!");
        }
    }

    static String checkWinner() {
        String[] b = board;
        String[][] lines = {
            {b[0], b[1], b[2]}, {b[3], b[4], b[5]}, {b[6], b[7], b[8]},
            {b[0], b[3], b[6]}, {b[1], b[4], b[7]}, {b[2], b[5], b[8]},
            {b[0], b[4], b[8]}, {b[2], b[4], b[6]}
        };

        for (String[] line : lines) {
            if (line[0].equals(line[1]) && line[1].equals(line[2])) {
                return line[0]; // Return "X" or "O"
            }
        }

        // Check for draw
        for (String s : b) {
            if (s.matches("\\d")) {
                return null; // Game is still ongoing
            }
        }

        return "draw";
    }

    static void printBoard() {
        System.out.println("|---|---|---|");
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("| %s | %s | %s |%n", board[i], board[i + 1], board[i + 2]);
            System.out.println("|-----------|");
        }
    }
}
