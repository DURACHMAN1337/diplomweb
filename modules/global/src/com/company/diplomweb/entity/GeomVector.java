package com.company.diplomweb.entity;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

import java.util.ArrayList;

@MetaClass(name = "diplomweb_NewEntity")
public class GeomVector extends BaseUuidEntity {
    private static final long serialVersionUID = 4782222288099675295L;


    @MetaProperty
    private ArrayList<Integer> coordinates;

    public GeomVector(ArrayList<Integer> coordinates) {

    }

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }
}