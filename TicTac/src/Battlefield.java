public class Battlefield {

    private static final int DEFAULT_SIZE = 3;
    private int size;
    private byte[][] matrix;
    private byte[][][] matrixHistory;
    private byte step = 0;

    public Battlefield() {
        this(DEFAULT_SIZE);
    }

    public Battlefield(int battlefield_size) {
        size = battlefield_size;
        matrix = new byte[size][size];
        matrixHistory = new byte[size*size][size][size];
        for(int i=0; i< size; i++) {
            for(int j=0; j < size; j++) {
                matrix[i][j] = 0;
            }
        }
        backupMatrix();

    }

    public byte[][] getMatrix() {
        return matrix;
    }

    public int getSize() {
        return size;
    }

    public boolean updateMatrix(byte player, int row, int col) {
        boolean canUpdate = (row >= 0 && col >= 0 && row < size && col < size && matrix[row][col] == 0 );
        if(canUpdate) {
            backupMatrix();
            step++;
            matrix[row][col] = player;
        }
        return canUpdate;
    }

    public void revertMatrix() {
        step--;
        step--;
        for(int i=0; i<size; i++) {
            System.arraycopy(matrixHistory[step][i], 0, matrix[i], 0, size);
        }
    }

    private void backupMatrix() {
        for(int i=0; i<size; i++) {
            System.arraycopy(matrix[i], 0, matrixHistory[step][i], 0, size);
        }
    }

    public boolean checkEmpty() {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(matrix[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] getFirstEmpty() {
        int[] target = {-1, -1};
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(matrix[i][j] == 0) {
                    target[0] = i;
                    target[1] = j;
                    return target;
                }
            }
        }
        return target;
    }

    public int[] getFirstEmptyInRow(int row) {
        int[] target = {row, -1};
        for(int i=0; i<size; i++) {
            if(matrix[row][i] == 0) {
                target[1] = i;
                return target;
            }
        }
        return target;
    }

    public int[] getFirstEmptyInCol(int col) {
        int[] target = {-1, col};
        for(int i=0; i<size; i++) {
            if(matrix[i][col] == 0) {
                target[0] = i;
                return target;
            }
        }
        return target;
    }

    public int[] getFirstEmptyInDiagonal1() {
        int[] target = {-1, -1};
        for(int i=0; i<size; i++) {
            if(matrix[i][i] == 0) {
                target[0] = i;
                target[1] = i;
                return target;
            }
        }
        return target;
    }

    public int[] getFirstEmptyInDiagonal2() {
        int[] target = {-1, -1};
        for(int i=0; i<size; i++) {
            if(matrix[i][size - 1 - i] == 0) {
                target[0] = i;
                target[1] = size - 1 - i;
                return target;
            }
        }
        return target;
    }

    public byte[] getRowProfile(int row) {
        byte profile[] = {0, 0, 0};
        for(int i=0; i<size; i++) {
            profile[matrix[row][i]]++;
        }
        return profile;
    }

    public byte[] getColProfile(int col) {
        byte profile[] = {0, 0, 0};
        for(int i=0; i<size; i++) {
            profile[matrix[i][col]]++;
        }
        return profile;
    }

    public byte[] getDiagonal1Profile() {
        byte profile[] = {0, 0, 0};
        for(int i=0; i<size; i++) {
            profile[matrix[i][i]]++;
        }
        return profile;
    }

    public byte[] getDiagonal2Profile() {
        byte profile[] = {0, 0, 0};
        for(int i=0; i<size; i++) {
            profile[matrix[i][size - 1 - i]]++;
        }
        return profile;
    }

}
