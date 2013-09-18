package com.battleship.view;

import com.battleship.model.Battle;
import com.battleship.model.Battlefield;
import java.util.Scanner;

public class ConsoleView extends BaseView {

    private static final String[] COL_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T"};
    private static final String[] CELL_STATES = {"     ", "  •  ", " ███ ", " ░░░ "};
    private static final String WRAP = "      ";
    private Scanner scanner;

    public ConsoleView(Battle battle) {
        super(battle);
        scanner = new Scanner(System.in);
    }

    public void gameHello() {
        printLine("\n");
        printLine("Battlefield game started!");
        printLine("\n");
    }

    public int askSomethingList(String subject, String[] variants) {

        printLine(subject);
        for(int i=0; i<variants.length; i++) {
            printLine(i+1 + ". " + variants[i]);
        }
        String consoleInput = scanner.nextLine();
        if(consoleInput.length() == 1) {
            try {
                return Integer.parseInt(consoleInput) - 1;
            } catch (NumberFormatException e) {}
        }
        return -1;

    }

    public String askSomethingString(String subject) {
        printLine(subject);
        String consoleInput = scanner.nextLine();
        return consoleInput;
    }

    public int askGameMode() {
        return askSomethingList("Select game mode:", battle.getGameModes());
    }

    public int askNetworkGameMode() {
        return askSomethingList("Select network game mode:", battle.getNetworkGameModes());
    }

    public int[] askShipPosition(int shipType, int shipSize, int shipCell) {
        String strPosition = askSomethingString("Setup ship #" + shipType + " [" + shipCell + "/" + shipSize + "]");
        return getPositionByCode(strPosition);
    }

    public String askSreverAddress() {
        return askSomethingString("Enter server IP address:");
    }

    private int[] getPositionByCode(String strPosition) {

        int[] position = {-1, -1};

        if(strPosition.length() == 2) {
            position[0] = getColByName(strPosition.substring(0, 1));
            try {
                position[1] = Integer.parseInt(strPosition.substring(1,2));
            } catch (NumberFormatException e) {}
        }

        return position;

    }

    private int getColByName(String name) {
        int col = -1;
        name = name.toUpperCase();
        for(int i=0; i< COL_NAMES.length; i++) {
            if(name.equals(COL_NAMES[i])) {
                col = i;
            }
        }
        return col;
    }

    public void printFields() {

        Battlefield[] fields = battle.getFields();
        int size = fields[0].getFieldSize();
        int count = fields.length;
        int rowCount = size*2+3;
        String[] outText = new String[rowCount];
        for(int row=0; row<rowCount; row++) {
            outText[row] = "";
        }

        for(int i=0; i<count; i++) {
            outText[0] += WRAP + "╔═════╦";
            outText[1] += WRAP + "║  #  ║";
            outText[2] += WRAP + "╠═════╬";
            for(int col=0; col < size; col++) {
                outText[0] += "═════";
                outText[1] += "  " + COL_NAMES[col] + "  ";
                outText[2] += "═════";
                if(col == size-1) {
                    outText[0] += "╗";
                    outText[1] += "║";
                    outText[2] += "╣";
                }
                else {
                    outText[0] += "╤";
                    outText[1] += "│";
                    outText[2] += "╪";
                }
            }

            int[][] field = fields[i].getField();

            for(int y=0; y<size; y++) {
                outText[y*2+3] += WRAP + "║  " + y + "  ║";
                if(y == size-1) {
                    outText[y*2+4] += WRAP + "╚═════╩";
                }
                else {
                    outText[y*2+4] += WRAP + "╟─────╫";
                }
                for(int x=0; x<size; x++) {
                    outText[y*2+3] += CELL_STATES[field[x][y]];
                    if(x == size-1) {
                        outText[y*2+3] += "║";
                        if(y == size-1) {
                            outText[y*2+4] += "═════╝";
                        }
                        else {
                            outText[y*2+4] += "─────╢";
                        }
                    }
                    else {
                        outText[y*2+3] += "│";
                        if(y == size-1) {
                            outText[y*2+4] += "═════╧";
                        }
                        else {
                            outText[y*2+4] += "─────┼";
                        }
                    }
                }
            }

        }

        printLine("\n\n");
        for(int row=0; row<rowCount; row++) {
            printLine(outText[row]);
        }
        printLine("\n\n");

    }

    public void printLine(String str) {
        System.out.println(str);
    }

}
