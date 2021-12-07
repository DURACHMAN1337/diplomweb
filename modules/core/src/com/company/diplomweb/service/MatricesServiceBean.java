package com.company.diplomweb.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(MatricesService.NAME)
public class MatricesServiceBean implements MatricesService {
    @Override
    public int[][] multiplyMatrices(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b.length];
        for (int i = 0; i < a.length; ++i)
            for (int j = 0; j < b.length; ++j)
                for (int k = 0; k < a.length; ++k)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    @Override
    public int[][] multiplyMatrixByNumber(int[][] matrix, int num) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] *= num;
            }
        }
        return matrix;
    }

    @Override
    public void printListOfMatrices(ArrayList<int[][]> list) {
        for (int[][] ints : list) {
            printMatrix(ints);
            System.out.println();
        }
    }

    @Override
    public void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < ints.length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public int[][] matricesSum(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    @Override
    public int[][] matricesMinus(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
        return c;
    }
}