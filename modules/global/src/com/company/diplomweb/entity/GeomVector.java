package com.company.diplomweb.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.ArrayList;


public class GeomVector {
    private static final long serialVersionUID = 4782222288099675295L;



    private ArrayList<Integer> coordinates;

    public GeomVector(ArrayList<Integer> coordinates) {
        this.coordinates=coordinates;
    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }
}