package com.company.diplomweb.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

@Table(name = "DIPLOMWEB_ALGEBRA_LIE")
@Entity(name = "diplomweb_AlgebraLie")
public class AlgebraLie extends StandardEntity {
    private static final long serialVersionUID = -8383281355009735099L;

    @Column(name = "TYPE_")
    private String type;

    @Column(name = "MATRICES_H")
    private ArrayList<int[][]> matricesH;

    @Column(name = "SPECIFIC_MATRICES")
    private ArrayList<int[][]> specificMatrices;

    @Column(name = "VECTORS_A")
    private ArrayList<GeomVector> vectorsA;

    @Column(name = "VECTORS_B")
    private ArrayList<GeomVector> vectorsB;

    public AlgebraLie() {
    }

    public ArrayList<int[][]> getSpecificMatrices() {
        return specificMatrices;
    }

    public void setSpecificMatrices(ArrayList<int[][]> specificMatrices) {
        this.specificMatrices = specificMatrices;
    }

    public ArrayList<int[][]> getMatricesH() {
        return matricesH;
    }

    public void setMatricesH(ArrayList<int[][]> matricesH) {
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