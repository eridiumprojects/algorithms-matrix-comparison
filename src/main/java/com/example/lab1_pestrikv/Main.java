package com.example.lab1_pestrikv;

public class Main {
    public static void main(String[] args) {
        int rows = (int) Math.pow(2, 1), columns = (int) Math.pow(2, 13); //input rows value
        int firstGenerationTarget = 2 * rows + 1;
        int secondGenerationTarget = 16 * rows + 1;
        int[][] firstMatrix = firstMatrixGeneration(rows, columns);
        int[][] secondMatrix = secondMatrixGeneration(rows, columns);
        scoring(firstMatrix, secondMatrix, firstGenerationTarget, secondGenerationTarget);
    }

    public static int[][] firstMatrixGeneration(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = ((columns / rows) * i + j) * 2;
            }
        }
        return arr;
    }

    private static int[][] secondMatrixGeneration(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = ((columns / rows) * i * j) * 2;
            }
        }
        return arr;
    }

    public static int binarySearch(int[][] arr, int target) {
        int columns = arr[0].length - 1;
        int rows = 0;
        while (columns >= 0 && rows < arr.length) {
            if (target > arr[rows][columns]) {
                ++rows;
            } else if (target < arr[rows][columns]) {
                columns = entryFirst(target, -1, columns, arr[rows]);
            } else {
                return columns;
            }
        }
        return -1;
    }


    public static int exponentialSearch(int[][] arr, int target, int value) {
        int rows = 0;
        int columns = arr[0].length - 1;
        while (columns >= 0 && rows < arr.length) {
            if (arr[rows][columns] > target) {
                if (columns > Math.pow(2, 5)) {
                    while (arr[rows][Math.abs(columns - value)] > target && columns >= value) {
                        if (value == 0) {
                            value++;
                        } else {
                            value *= 2;
                        }
                    }
                    value /= 2;
                } else {
                    value = 0;
                }
                columns = entryFirst(target, -1, columns - value, arr[rows]);
            } else if (arr[rows][columns] < target) {
                ++rows;
            } else {
                return columns;
            }
        }
        return -1;
    }

    public static int entryFirst(int target, int left, int right, int[] rows) {
        while (right - left > 1) {
            int middle = (right + left) / 2;
            if (rows[middle] > target) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return right - 1;
    }


    public static int linearSearch(int[][] arr, int target) {
        int columns = arr[0].length - 1;
        int rows = 0;
        while (columns >= 0 && rows < arr.length) {
            if (target > arr[rows][columns]) {
                ++rows;
            } else if (target < arr[rows][columns]) {
                --columns;
            } else {
                return columns;
            }
        }
        return -1;
    }

    public static void scoring(int[][] firstMatrix, int[][] secondMatrix,
                               int firstGenerationTarget, int secondGenerationTarget) {
        long startBin = System.nanoTime();
        int binValue = binarySearch(firstMatrix, firstGenerationTarget);
        long endBin = System.nanoTime();
        long startExp = System.nanoTime();
        int expValue = exponentialSearch(firstMatrix, firstGenerationTarget, 2);
        long endExp = System.nanoTime();
        long startLinear = System.nanoTime();
        int linearValue = linearSearch(firstMatrix, firstGenerationTarget);
        long endLinear = System.nanoTime();
        long startSecondExp = System.nanoTime();
        int secondExpVariationValue = exponentialSearch(secondMatrix, secondGenerationTarget, 2);
        long endSecondExp = System.nanoTime();
        float resultExp = endExp - startExp;
        float resultLinear = endLinear - startLinear;
        float resultBin = endBin - startBin;
        float resultSecondExp = endSecondExp - startSecondExp;
        System.out.println("b" + resultBin / 1000000 + "ns "
                + "exp" + resultExp / 1000000 + "ns "
                + "standard" + resultLinear / 1000000 + "ns "
                + "second exp " + resultSecondExp / 1000000 + "ns");
    }
}

