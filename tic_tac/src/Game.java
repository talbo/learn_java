import java.util.Scanner;

public class Game {

    private static final int BATTLEFIELD_SIZE = 5;
    private static final String EMPTY_CELL = " [ ] ";
    private static final String[] PLAYER_CELLS = {" [0] ", " [X] "};
    private static final String[] PLAYERS = {"TIC", "TAC"};
    private static final String[] ROW_NAMES = {"A", "B", "C", "D", "E"};
    private String[][] battlefield;

    public Game() {

        System.out.println("Wellcome to tic-tac game!");

        initBattlefield();
        printBattlefield();

        int currentPlayer = 0;
        boolean hasWinner = false;

        while(!hasWinner) {

            String target = askTarget(currentPlayer);
            boolean validStep = updateBattlefield(currentPlayer, target);

            if (validStep) {
                hasWinner = checkWin(currentPlayer);
                printBattlefield();
                if(currentPlayer == 0) currentPlayer = 1;
                else currentPlayer = 0;
            }
            else {
                System.out.println("Target error!");
            }

        }

    }

    private void initBattlefield() {
        battlefield = new String[BATTLEFIELD_SIZE][BATTLEFIELD_SIZE];
        for(int i=0; i< BATTLEFIELD_SIZE; i++) {
            for(int j=0; j< BATTLEFIELD_SIZE; j++) {
                battlefield[i][j] = EMPTY_CELL;
            }
        }
    }

    private boolean updateBattlefield(int player, String target) {
        int col = -1;
        int row = -1;
        if(target.length() == 2) {
            try {
                col = Integer.parseInt(target.substring(1,2)) - 1;
            } catch (NumberFormatException e) {}
            row = getRowByName(target.substring(0,1));
        }
        if (row >= 0 && col >= 0 && row < BATTLEFIELD_SIZE && col < BATTLEFIELD_SIZE && EMPTY_CELL.equals(battlefield[row][col]) ) {
            battlefield[row][col] = PLAYER_CELLS[player];
            return true;
        }
        else {
            return false;
        }
    }

    private int getRowByName(String name) {
        name = name.toUpperCase();
        for(int i=0; i< BATTLEFIELD_SIZE; i++) {
            if(name.equals(ROW_NAMES[i])) return i;
        }
        return -1;
    }

    private void printBattlefield() {
        String headRow = "\n  ";
        for(int i=0; i< BATTLEFIELD_SIZE; i++) {
            headRow += "  " + (i+1) + "  ";
        }
        System.out.println(headRow);
        for(int i=0; i< BATTLEFIELD_SIZE; i++) {
            String row = ROW_NAMES[i] + " ";
            for(int j=0; j< BATTLEFIELD_SIZE; j++) {
                row += battlefield[i][j];
            }
            System.out.println(row + "\n");
        }
    }

    private boolean checkWin(int player) {

        String playerCell = PLAYER_CELLS[player];
        boolean isWin = false;

        int diagonal1Count = 0;
        int diagonal2Count = 0;

        for(int i=0; i< BATTLEFIELD_SIZE; i++) {
            int rowCount = 0;
            int colCount = 0;
            if(playerCell.equals(battlefield[i][i])) {
                diagonal1Count++;
            }
            for(int j=0; j< BATTLEFIELD_SIZE; j++) {
                if(playerCell.equals(battlefield[i][j])) {
                    rowCount++;
                }
                if(playerCell.equals(battlefield[j][i])) {
                    colCount++;
                }
                if(playerCell.equals(battlefield[i][j]) && i+j==BATTLEFIELD_SIZE-1) {
                    diagonal2Count++;
                }
            }
            if(rowCount == BATTLEFIELD_SIZE || colCount == BATTLEFIELD_SIZE|| diagonal1Count == BATTLEFIELD_SIZE|| diagonal2Count == BATTLEFIELD_SIZE) {
                isWin = true;
                System.out.print("Player " + PLAYERS[player] + PLAYER_CELLS[player] + " is WINER!!!");
            }
        }

        return isWin;
    }

    private String askTarget(int player) {
        Scanner scaner = new Scanner(System.in);
        System.out.print("Player " + PLAYERS[player] + PLAYER_CELLS[player] + ": ");
        return scaner.nextLine();

    }

}
