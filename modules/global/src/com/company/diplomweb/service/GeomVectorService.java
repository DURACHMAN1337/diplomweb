package com.company.diplomweb.service;

import com.company.diplomweb.entity.GeomVector;

import java.util.ArrayList;

public interface GeomVectorService {
    String NAME = "diplomweb_GeomVectorService";
    String getStringView(GeomVector geomVector);
    String getStringView(ArrayList<GeomVector> geomVectors);
}