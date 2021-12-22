package com.company.diplomweb.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;


public class AlgebraLie {
    private static final long serialVersionUID = -8383281355009735099L;


    private String type;


    private ArrayList<Integer[][]> matricesH;


    private ArrayList<Integer[][]> specificMatrices;


    private ArrayList<GeomVector> vectorsA;


    private ArrayList<GeomVector> vectorsB;

    public AlgebraLie() {
    }

    public ArrayList<Integer[][]> getSpecificMatrices() {
        return specificMatrices;
    }

    public void setSpecificMatrices(ArrayList<Integer[][]> specificMatrices) {
        this.specificMatrices = specificMatrices;
    }

    public ArrayList<Integer[][]> getMatricesH() {
        return matricesH;
    }

    public void setMatricesH(ArrayList<Integer[][]> matricesH) {
        this.matricesH = matricesH;
    }

    public AlgebraType getType() {
        return type == null ? null : AlgebraType.fromId(type);
    }

    public void setType(AlgebraType type) {
        this.type = type == null ? null : type.getId();
    }

    public ArrayList<GeomVector> getVectorsB() {
        return vectorsB;
    }

    public void setVectorsB(ArrayList<GeomVector> vectorsB) {
        this.vectorsB = vectorsB;
    }

    public ArrayList<GeomVector> getVectorsA() {
        return vectorsA;
    }

    public void setVectorsA(ArrayList<GeomVector> vectorsA) {
        this.vectorsA = vectorsA;
    }
}