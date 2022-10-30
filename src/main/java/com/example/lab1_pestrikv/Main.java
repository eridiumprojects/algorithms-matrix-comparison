package com.example.lab1_pestrikv;

import static com.example.lab1_pestrikv.algorithms.binaryImplementation.binarySearch;
import static com.example.lab1_pestrikv.algorithms.exponentialImplementation.exponentialSearch;
import static com.example.lab1_pestrikv.generatedMatrixes.firstMatrixGeneration;
import static com.example.lab1_pestrikv.generatedMatrixes.secondMatrixGeneration;
import static com.example.lab1_pestrikv.algorithms.linearImplementation.linearSearch;

public class Main {
    public static void main(String[] args) {
        int rows = (int) Math.pow(2, 1), columns = (int) Math.pow(2, 13); //input rows value
        int firstGenerationTarget = 2 * rows + 1;
        int secondGenerationTarget = 16 * rows + 1;
        int[][] firstMatrix = firstMatrixGeneration(rows, columns);
        int[][] secondMatrix = secondMatrixGeneration(rows, columns);
        scoring(firstMatrix, secondMatrix, firstGenerationTarget, secondGenerationTarget);
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

