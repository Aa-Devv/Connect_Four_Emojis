import java.util.Scanner;

public class connectFour {
    String[][] board = new String[7][6];
    String[] players = {"🟠", "🟡"};
    int currentPlayer;
    String player;
    boolean done;

    public static void main(String[] args) {
        connectFour cf = new connectFour();
        Scanner s = new Scanner(System.in);
        cf.startGame();
        while (!cf.done) {
            int index;
            while (true) {
                try {
                    index = Integer.parseInt(s.next());
                } catch (Exception e) {
                    System.err.println("You must choose a column from 1 - 7\nTry again!");
                    continue;
                }
                break;
            }
            cf.newMove(Math.abs(((index % 8) - 1)));
        }
    }


    int insert(int x) {
        for (int i = board[0].length - 1; i > -1; i--)
            if (board[x][i] != null && board[x][i].equals("🟢"))
                return i;
        return -1;
    }

    void displayBoard() {
        System.out.println("1️⃣2️⃣3️⃣4️⃣5️⃣6️⃣7️⃣");
        for (int i = 0; i < board[0].length; i++) {
            for (String[] strings : board) System.out.print(strings[i]);
            System.out.println();
        }
    }

    boolean winChecker(int x, int y, String player) {
        //first we check vertically
        int winningCount = 1;
        for (int i = y + 1; i < board[0].length; i++)
            if (board[x][i].equalsIgnoreCase(player)) {
                winningCount++;
                if (winningCount == 4)
                    return true;

            } else break;

        //then we check horizontally
        winningCount = 0;
        for (int i = 0; i < board[0].length; i++) {
            for (String[] strings : board) {
                if (strings[i].equalsIgnoreCase(player)) {
                    winningCount++;
                    if (winningCount == 4)
                        return true;

                } else winningCount = 0;
            }
            winningCount = 0;
        }

        //then we check the left side diagonally
        //down
        winningCount = 1;
        for (int i = y + 1, j = x + 1; i < board[0].length && j < board.length; i++, j++) {
            if (board[j][i].equalsIgnoreCase(player)) {
                winningCount++;
                if (winningCount == 4)
                    return true;

            } else break;
        }
        //up

        for (int i = y - 1, j = x - 1; i > -1 && j > -1; i--, j--) {
            if (board[j][i].equalsIgnoreCase(player)) {
                winningCount++;
                if (winningCount == 4)
                    return true;

            } else break;
        }

        //then we check the right side diagonally
        //down
        winningCount = 1;
        for (int i = y + 1, j = x - 1; i < board[0].length && j > -1; i++, j--) {
            if (board[j][i].equalsIgnoreCase(player)) {
                winningCount++;
                if (winningCount == 4)
                    return true;

            } else break;
        }

        //up

        for (int i = y - 1, j = x + 1; i > -1 && j < board.length; i--, j++) {
            if (board[j][i].equalsIgnoreCase(player)) {
                winningCount++;
                if (winningCount == 4)
                    return true;

            } else break;
        }

        return false;
    }


    boolean drawChecker() {
        for (int i = 0; i < board.length; i++)
            if (insert(i) != -1)
                return false;
        return true;
    }

    void nextTurn() {
        currentPlayer = (currentPlayer + 1) % players.length;
        player = players[currentPlayer];
    }

    void startGame() {
        currentPlayer = 0;
        player = players[currentPlayer];
        for (int i = 0; i < board[0].length; i++)
            for (int j = 0; j < board.length; j++)
                board[j][i] = "🟢";
        displayBoard();
    }

    void newMove(int x) {
        int y = insert(x);
        if (y == -1) {
            System.out.println("This column is full!");
            displayBoard();
            return;
        }
        board[x][y] = player;
        if (winChecker(x, y, player)) {
            System.out.println(player + " wins!");
            done = true;
        } else if (drawChecker()) {
            System.out.println("It's a draw!");
            done = true;
        }
        displayBoard();
        nextTurn();
    }
}
