import java.util.Scanner;

public class Controller {

    private Battlefield model;
    private Viewer viewer;
    private Scanner scanner;

    private byte currentPlayer = 1;
    private boolean hasWinner = false;
    private boolean hasEmpty = true;
    private static final boolean SINGLE_PLAYER = true;

    public Controller(Battlefield model, Viewer viewer) {

        this.model = model;
        this.viewer = viewer;

        scanner = new Scanner(System.in);

        viewer.printHello();
        viewer.printMatrix();

        while(!hasWinner && hasEmpty) {

            int[] target;
            if(SINGLE_PLAYER && currentPlayer == 2) {
                target = astralTarget();
            }
            else {
                target = askTarget();
            }

            boolean validStep = model.updateMatrix(currentPlayer, target[0], target[1]);

            if (validStep) {
                hasWinner = checkWinner();
                hasEmpty = model.checkEmpty();
                viewer.printMatrix();
                if(!hasEmpty) viewer.gameOver();
                switchPlayer();
            }
            else {
                System.out.println("Target error! " + target[0] + " " + target[1]);
            }

        }

    }

    private int[] askTarget() {
        int[] target = {-1, -1};
        viewer.printTargetQuestion(currentPlayer);
        String strTarget = scanner.nextLine();

        if(strTarget.length() == 2) {
            target[0] = viewer.getRowByName(strTarget.substring(0, 1));
            try {
                target[1] = Integer.parseInt(strTarget.substring(1,2)) - 1;
            } catch (NumberFormatException e) {}
        }
        else if(strTarget.equals("back")) {
            model.revertMatrix();
            viewer.printMatrix();
            return askTarget();
        }

        return target;
    }

    private int[] astralTarget() {
        /*
        * искуственный интеллект:
        * 1. проверить возможность победы в один ход (запомнить лучший, если не в один)
        * 2. не дать победить противнику в один ход
        * */

        int[] firstEmpty = model.getFirstEmpty();
        int[] target = firstEmpty;
        int[] wellTarget = firstEmpty;

        int size = model.getSize();
        boolean canWin = false;
        int[] canWinTarget = firstEmpty;
        boolean canOpponentWin = false;
        int[] canOpponentWinTarget = firstEmpty;
        byte opponent = getOpponent(currentPlayer);

        byte maxLine = 0;

        for(int i=0; i<size; i++) {

            byte[] rowProfile = model.getRowProfile(i);
            if(rowProfile[currentPlayer] == size-1 && rowProfile[0] == 1) {
                canWin = true;
                canWinTarget = model.getFirstEmptyInRow(i);
            }
            else if(rowProfile[opponent] == size-1 && rowProfile[0] == 1) {
                canOpponentWin = true;
                canOpponentWinTarget = model.getFirstEmptyInRow(i);
            }
            else if(rowProfile[opponent] == 0 && rowProfile[currentPlayer] > maxLine) {
                maxLine = rowProfile[currentPlayer];
                wellTarget = model.getFirstEmptyInRow(i);
            }

            byte[] colProfile = model.getColProfile(i);
            if(colProfile[currentPlayer] == size-1 && colProfile[0] == 1) {
                canWin = true;
                canWinTarget = model.getFirstEmptyInCol(i);
            }
            else if(colProfile[opponent] == size-1 && colProfile[0] == 1) {
                canOpponentWin = true;
                canOpponentWinTarget = model.getFirstEmptyInCol(i);
            }
            else if(colProfile[opponent] == 0 && colProfile[currentPlayer] > maxLine) {
                maxLine = colProfile[currentPlayer];
                wellTarget = model.getFirstEmptyInCol(i);
            }

        }

        byte[] diagonal1Profile = model.getDiagonal1Profile();
        if(diagonal1Profile[currentPlayer] == size-1 && diagonal1Profile[0] == 1) {
            canWin = true;
            canWinTarget = model.getFirstEmptyInDiagonal1();
        }
        else if(diagonal1Profile[opponent] == size-1 && diagonal1Profile[0] == 1) {
            canOpponentWin = true;
            canOpponentWinTarget = model.getFirstEmptyInDiagonal1();
        }
        else if(diagonal1Profile[opponent] == 0 && diagonal1Profile[currentPlayer] > maxLine) {
            maxLine = diagonal1Profile[currentPlayer];
            wellTarget = model.getFirstEmptyInDiagonal1();
        }

        byte[] diagonal2Profile = model.getDiagonal2Profile();
        if(diagonal2Profile[currentPlayer] == size-1 && diagonal2Profile[0] == 1) {
            canWin = true;
            canWinTarget = model.getFirstEmptyInDiagonal2();
        }
        else if(diagonal2Profile[opponent] == size-1 && diagonal2Profile[0] == 1) {
            canOpponentWin = true;
            canOpponentWinTarget = model.getFirstEmptyInDiagonal2();
        }
        else if(diagonal2Profile[opponent] == 0 && diagonal2Profile[currentPlayer] > maxLine) {
            maxLine = diagonal2Profile[currentPlayer];
            wellTarget = model.getFirstEmptyInDiagonal2();
        }

        if(canWin) {
            return canWinTarget;
        }
        else if(canOpponentWin) {
            return canOpponentWinTarget;
        }
        else if (maxLine > 0) {
            return wellTarget;
        }

        return target;
    }

    private void switchPlayer() {
        if(currentPlayer == 1) currentPlayer = 2;
        else currentPlayer = 1;
    }

    private byte getOpponent(byte player) {
        if(player == 1) return 2;
        else return 1;
    }

    private boolean checkWinner() {
        int size = model.getSize();
        boolean hasWinner = false;
        for(int i=0; i<size; i++) {
            if(model.getRowProfile(i)[currentPlayer] == size || model.getColProfile(i)[currentPlayer] == size) {
                hasWinner = true;
            }
        }
        if(model.getDiagonal1Profile()[currentPlayer] == size || model.getDiagonal2Profile()[currentPlayer] == size) {
            hasWinner = true;
        }
        if(hasWinner) viewer.printWinner(currentPlayer);
        return hasWinner;
    }

}
