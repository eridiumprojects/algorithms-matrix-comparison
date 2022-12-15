package com.example.lab1_pestrikv.algorithms;

public class linearImplementation {
    public static int linearSearch(int[][] arr, int target) {
        int rows = 0;
        int columns = arr[0].length - 1;
        while (columns > -1 && rows < arr.length) {
            if (target > arr[rows][columns]) {
                rows += 1;
            } else if (target < arr[rows][columns]) {
                columns -= 1;
            } else {
                return columns;
            }
        }
        return -1;
    }
}