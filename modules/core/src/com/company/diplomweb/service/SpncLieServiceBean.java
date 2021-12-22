package com.company.diplomweb.service;

import com.company.diplomweb.entity.GeomVector;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;

@Service(SpncLieService.NAME)
public class SpncLieServiceBean implements SpncLieService {

    @Inject
    private MatricesService matricesService;

    @Override
    public String getAlgebraName() {
        return "Lie Algebra Sp_n(C)";
    }

    @Override
    public ArrayList<int[][]> generateMatricesH(int dim) {
        ArrayList<int[][]> result = new ArrayList<>();
        if (dim % 2 == 0) {
            int[][] matrix;

            for (int i = 0; i < dim / 2; i++) {
                matrix = new int[dim][dim];
                matrix[i][i] = 1;
                matrix[(dim - 1) - i][(dim - 1) - i] = -1;

                result.add(matrix);
            }
        } else {
            System.err.println("Размерность должна быть вида 2m");
        }
        return result;
    }

    @Override
    public ArrayList<int[][]> generateSpecificMatrices(int dim) {
        ArrayList<int[][]> result = new ArrayList<>();
        if (dim % 2 == 0) {
            int[][] matrix_fi;
            int[][] matrix_fij_1;
            int[][] matrix_fi_j;
            int[][] nullMatrix = new int[dim][dim];

            //Генерация f_i
            for (int i = 0; i < dim; i++) {
                for (int j = dim - 1; j >= 0; j--) {
                    matrix_fi = new int[dim][dim];
                    if (Arrays.deepEquals(matrix_fi, nullMatrix)) {
                        matrix_fi[i][j] = -1;
                        i++;
                        result.add(matrix_fi);
                    }
                }
            }

            //Генерация f_ij
            for (int i = 0; i < dim / 2; i++) {
                for (int j = 0; j < dim / 2; j++) {
                    matrix_fij_1 = new int[dim][dim];
                    if (i != j) {
                        matrix_fij_1[i][j] = -1;
                        matrix_fij_1[(dim - 1) - j][(dim - 1) - i] = 1;
                        result.add(matrix_fij_1);
                    }
                }
            }

            //Генерация f_i-j
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    int k = dim / 2;
                    if ((i > k && j < k && (i + j != dim - 1)) || (i < k && j > k && (i + j != dim - 1))) {
                        matrix_fi_j = new int[dim][dim];
                        matrix_fi_j[i][j] = 1;
                        matrix_fi_j[(dim - 1) - j][(dim - 1) - i] = 1;
                        result.add(matrix_fi_j);
                    }
                }
            }
        } else {
            System.err.println("Размерность должна быть вида 2m");
        }
        return result;

    }

    @Override
    public ArrayList<GeomVector> generateVectorsA(ArrayList<int[][]> h, ArrayList<int[][]> f) {
        int dim = h.get(0).length;
        int[][] nullMatrix = new int[dim][dim];
        ArrayList<int[][]> basis = new ArrayList<>();
        ArrayList<int[][]> temp = new ArrayList<>();
        ArrayList<Integer> vectorsA = new ArrayList<>();
        ArrayList<ArrayList<Integer>> vectorsAA = new ArrayList<>();
        ArrayList<GeomVector> vectors = new ArrayList<>();
        basis.addAll(h);
        basis.addAll(f);

        for (int i = 0; i < h.size(); i++) {
            for (int[][] matrix : basis) {
                int[][] newMatrix = matricesService.matricesMinus(matricesService.multiplyMatrices(h.get(i), matrix), matricesService.multiplyMatrices(matrix, h.get(i)));
                temp.add(newMatrix);
                if (Arrays.deepEquals(newMatrix, nullMatrix)) {
                    vectorsA.add(0);
                } else {
                    if (Arrays.deepEquals(newMatrix, matrix)) {
                        vectorsA.add(1);
                    } else if (Arrays.deepEquals(newMatrix, matricesService.multiplyMatrixByNumber(matrix, -1))) {
                        vectorsA.add(-1);
                    } else {
                        vectorsA.add(2);
                    }
                }
            }
        }

        final int l = vectorsA.size() / h.size();
        final int n = vectorsA.size();
        for (int i = 0; i < n; i += l) {
            vectorsAA.add(new ArrayList<Integer>(vectorsA.subList(i, Math.min(n, i + l))));
        }
        for (ArrayList<Integer> list : vectorsAA) {
            vectors.add(new GeomVector(list));
        }
        return vectors;

    }

    @Override
    public ArrayList<GeomVector> generateVectorsB(ArrayList<GeomVector> vectorsA, int modP) {
        ArrayList<GeomVector> vectorsB = new ArrayList<>();
        for (GeomVector geomVector : vectorsA) {
            ArrayList<Integer> newCoordinates = new ArrayList<>(geomVector.getCoordinates());
            for (int i = 0; i < newCoordinates.size(); i++) {
                if (newCoordinates.get(i) < 0) {
                    newCoordinates.set(i, newCoordinates.get(i) + modP);
                }
            }
            vectorsB.add(new GeomVector(newCoordinates));
        }
        return vectorsB;
    }
}