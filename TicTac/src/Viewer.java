public class Viewer {

    private static final String[] ROW_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private static final char[] CELL_TYPES = {' ', 'X', '0'};
    private static final String[] PLAYERS = {"EMPTY", "TIC", "TAC"};

    private Battlefield model;

    public Viewer(Battlefield model) {
        this.model = model;
    }

    public void printHello() {
        System.out.println("Tic-tac game started!");
        System.out.println("Print A1 to select row 'A', collumn '1'");
        System.out.println("Print 'back' to cancel");
    }

    public void printTargetQuestion(byte player) {
        System.out.print("Player " + PLAYERS[player] + " [" + CELL_TYPES[player] + "]: ");
    }

    public void printMatrix() {
        byte[][] matrix = model.getMatrix();
        int size = matrix.length;

        String headRow = "\n  ";
        for(int i=0; i< size; i++) {
            headRow += "  " + (i+1) + "  ";
        }
        System.out.println(headRow);
        for(int i=0; i< size; i++) {
            String row = ROW_NAMES[i] + " ";
            for(int j=0; j< size; j++) {
                row += " [" + CELL_TYPES[matrix[i][j]] + "] ";
            }
            System.out.println(row + "\n");
        }

    }

    public void printWinner(byte player) {
        System.out.print("Player " + PLAYERS[player] + " [" + CELL_TYPES[player] + "] - WINNER!!!");
    }

    public void gameOver() {
        System.out.print("Game over!");
    }

    public int getRowByName(String name) {
        name = name.toUpperCase();
        for(int i=0; i< ROW_NAMES.length; i++) {
            if(name.equals(ROW_NAMES[i])) return i;
        }
        return -1;
    }

    public String getNameByRow(int row) {
        return ROW_NAMES[row];
    }

}
