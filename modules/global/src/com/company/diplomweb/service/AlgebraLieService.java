package com.company.diplomweb.service;

import com.company.diplomweb.entity.GeomVector;

import java.util.ArrayList;

public interface AlgebraLieService {
    String NAME = "diplomweb_AlgebraLieService";

    String getAlgebraName();

    ArrayList<int[][]> generateMatricesH(int dim);

    ArrayList<int[][]> generateSpecificMatrices(int dim);

    ArrayList<GeomVector> generateVectorsA(ArrayList<int[][]> h, ArrayList<int[][]> f);

    ArrayList<GeomVector> generateVectorsB(ArrayList<GeomVector> vectorsA, int modP);

}