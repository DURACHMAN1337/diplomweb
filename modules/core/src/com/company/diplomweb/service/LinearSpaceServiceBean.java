package com.company.diplomweb.service;

import com.company.diplomweb.entity.GeomVector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(LinearSpaceService.NAME)
public class LinearSpaceServiceBean implements LinearSpaceService {

    @Override
    public Set<GeomVector> generateLinearSpace(List<ArrayList<Integer>> combinations, ArrayList<GeomVector> vectors, int mod) {
        Set<GeomVector> linearSpace = new HashSet<>(vectors);
        for (ArrayList<Integer> combination : combinations) {
            linearSpace.add(calculateLinearCombination(vectors, combination, mod));
        }
        return linearSpace;
    }

    @Override
    public GeomVector calculateLinearCombination(ArrayList<GeomVector> vectors, ArrayList<Integer> combination, int mod) {
        ArrayList<GeomVector> newVectors = new ArrayList<>();

        for (int i = 0; i < combination.size(); i++) {
            newVectors.add(GeomVector.multiplyByNumber(mod, vectors.get(i), combination.get(i)));
        }

        return GeomVector.sumVectors(mod, newVectors);

    }

    @Override
    public int calculateDistance(Set<GeomVector> LinearSpace) {
        int min = 100;
        int weight = 0;
        for (GeomVector geomVector : LinearSpace) {
            for (int i = 0; i < geomVector.getCoordinates().size(); i++) {
                if (geomVector.getCoordinates().get(i) == 0)
                    weight++;
            }
            if (weight < min)
                min = weight;
            weight = 0;
        }
        return min;

    }

    @Override
    public ArrayList<ArrayList<Integer>> generateConstants(int dim, int mod) {
        double vectorsQuantity = Math.pow(mod, dim);
        ArrayList<ArrayList<Integer>> vectors = new ArrayList<>();
        List<Integer> vector = getEmptyVector(dim);
        vectors.add(new ArrayList<>(vector));
        while (vectors.size() != vectorsQuantity) {
            vector = iterateVector(vector, mod);
            if (!vectors.contains(vector)) {
                vectors.add(new ArrayList<>(vector));
            }
        }
        return vectors;

    }

    private static List<Integer> getEmptyVector(int dim) {
        List<Integer> vector = new ArrayList<>();
        for (int j = 0; j < dim; j++) {
            vector.add(0);
        }
        return vector;
    }

    private static List<Integer> iterateVector(List<Integer> initialVector, int mod) {
        List<Integer> vector = new ArrayList<>(initialVector);
        for (int i = vector.size() - 1; i >= 0; i--) {
            int iteratedInt = (vector.get(i) + 1) % mod;
            vector.set(i, iteratedInt);
            if (iteratedInt != 0) {
                break;
            }
        }
        return vector;
    }
}