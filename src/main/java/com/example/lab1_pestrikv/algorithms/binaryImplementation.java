package com.example.lab1_pestrikv.algorithms;

public class binaryImplementation {
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
