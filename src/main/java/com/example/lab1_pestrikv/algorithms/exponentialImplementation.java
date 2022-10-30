package com.example.lab1_pestrikv.algorithms;

public class exponentialImplementation {
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
}
