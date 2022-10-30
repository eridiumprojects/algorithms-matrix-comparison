package com.example.lab1_pestrikv.algorithms;

public class linearImplementation {
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
}
