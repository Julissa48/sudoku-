public class SudokuBoard{
public static void main(String[] args){

    // randomized 1–9 matrix
    int[][] matrix1 = new int[3][3];
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            int value;
            boolean found;
            do {
                value = (int)(Math.random() * 9) + 1;
                found = false;
                // check if value already exists
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        if (matrix1[x][y] == value) {
                            found = true;
                        }
                    }
                }
            } while (found == true);
            matrix1[i][j] = value;
        }
    }

    //2array 2 copy 1 and then switch first row to bottom
    int[][] matrix2 = newMatrix(matrix1);
    matrix2 = moveFirstRowToBottom(matrix2);

    //2array 3 copy 2 then switch first row to bottom
    int[][] matrix3 = newMatrix(matrix2);
    matrix3 = moveFirstRowToBottom(matrix3);

    //2array 4 copy 3 switch first row to bottom + first column to the end
    int[][] matrix4 = newMatrix(matrix3);
    matrix4 = moveFirstRowToBottom(matrix4);
    matrix4 = moveFirstColToEnd(matrix4);

    //2array 5 copy 4 switch first row to bottom
    int[][] matrix5 = newMatrix(matrix4);
    matrix5 = moveFirstRowToBottom(matrix5);

    //2array 6 copy 5 switch first row to bottom
    int[][] matrix6 = newMatrix(matrix5);
    matrix6 = moveFirstRowToBottom(matrix6);

    //2array 7 copy 6 switch first row to bottom + first element at bottom
    int[][] matrix7 = newMatrix(matrix6);
    matrix7 = moveFirstRowToBottom(matrix7);
    matrix7 = moveFirstElementToEnd(matrix7);

    //2array 8 switch first row to bottom
    int[][] matrix8 = newMatrix(matrix7);
    matrix8 = moveFirstRowToBottom(matrix8);

    //2array 9 switch first row to bottom
    int[][] matrix9 = newMatrix(matrix8);
    matrix9 = moveFirstRowToBottom(matrix9);

    //stores them together
    int[][][] matrices = {
        matrix1, matrix2, matrix3,
        matrix4, matrix5, matrix6,
        matrix7, matrix8, matrix9
    };
    printSudoku(matrices);
}

    // prints all matrices 
    public static void printSudoku(int[][][] m) {
        System.out.println("+-------+-------+-------+");
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int row = 0; row < 3; row++) {
                for (int blockCol = 0; blockCol < 3; blockCol++) {
                    int block = blockRow * 3 + blockCol;
                    System.out.print("| ");
                    for (int col = 0; col < 3; col++) {
                        System.out.print(m[block][row][col] + " ");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println("+-------+-------+-------+");
        }
    }

    //method that copys last matrix
    public static int[][] newMatrix(int[][] last) {
        int[][] current = new int[last.length][last[0].length];
        for (int i = 0; i < last.length; i++) {
            for (int j = 0; j < last[0].length; j++) {
                current[i][j] = last[i][j];
            }
        }
        return current;
    }

    // method that puts the first row on the bottom
    public static int[][] moveFirstRowToBottom(int[][] last) {
        int[][] current = newMatrix(last);
        int[] firstRow = current[0];
        for (int i = 0; i < current.length - 1; i++) {
            current[i] = current[i + 1];
        }
        current[current.length - 1] = firstRow;
        return current;
    }

    //method that puts first column to the end
    public static int[][] moveFirstColToEnd(int[][] last) {
        int[][] current = newMatrix(last);
        for (int i = 0; i < current.length; i++) {
            int firstVal = current[i][0];
            for (int j = 0; j < current[i].length - 1; j++) {
                current[i][j] = current[i][j + 1];
            }
            current[i][current[i].length - 1] = firstVal;
        }
        return current;
    }

    //method to put first element at 0X0 to end
    public static int[][] moveFirstElementToEnd(int[][] last) {
        int[][] current = newMatrix(last);
        int rows = current.length;
        int cols = current[0].length;
        int firstVal = current[0][0];
        for (int i = 0; i < rows * cols - 1; i++) {
            current[i / cols][i % cols] = current[(i + 1) / cols][(i + 1) % cols];
        }
        current[rows - 1][cols - 1] = firstVal;
        return current;
    }
}