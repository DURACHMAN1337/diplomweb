package com.company.diplomweb.service;

import com.company.diplomweb.entity.GeomVector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(GeomVectorService.NAME)
public class GeomVectorServiceBean implements GeomVectorService {

    @Override
    public String getStringView(GeomVector geomVector) {
        StringBuilder view = new StringBuilder("{");
        ArrayList<Integer> vector = geomVector.getCoordinates();
        vector.forEach(coordinate-> view.append(coordinate).append(", "));
        view.delete(view.length()-2,view.length()-1);
        view.append("}");
        return String.valueOf(view);
    }

    @Override
    public String getStringView(ArrayList<GeomVector> geomVectors) {
        StringBuilder view = new StringBuilder("[");
        geomVectors.forEach(geomVector -> view.append(getStringView(geomVector)).append(", "));
        view.delete(view.length()-2,view.length()-1);
        view.append("]");
        return String.valueOf(view);
    }
}