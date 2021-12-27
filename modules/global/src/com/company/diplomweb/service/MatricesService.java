package com.company.diplomweb.service;

import java.util.ArrayList;
import java.util.List;

public interface MatricesService {
    String NAME = "diplomweb_MatrciesService";

    int[][] multiplyMatrices(int[][] a, int[][] b);

    int[][] multiplyMatrixByNumber(int[][] matrix, int num);

    void printListOfMatrices(List<int[][]> list);

    void printMatrix(int[][] matrix);

    int[][] matricesSum(int[][] a, int[][] b);

    int[][] matricesMinus(int[][] a, int[][] b);

    String getMatrixStringView(int[][] matrix);

    String getMatrixListStringView(List<int[][]> list);
}