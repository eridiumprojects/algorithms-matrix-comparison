package com.example.lab1_pestrikv;

public class generatedMatrixes {
    public static int[][] firstMatrixGeneration(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = ((columns / rows) * i + j) * 2;
            }
        }
        return arr;
    }

    public static int[][] secondMatrixGeneration(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = ((columns / rows) * i * j) * 2;
            }
        }
        return arr;
    }
}
