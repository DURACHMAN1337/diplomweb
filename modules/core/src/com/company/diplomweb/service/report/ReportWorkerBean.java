package com.company.diplomweb.service.report;


import com.company.diplomweb.entity.GeomVector;
import com.company.diplomweb.service.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.*;

@Component(ReportWorker.NAME)
public class ReportWorkerBean implements ReportWorker {
    public static final String oncLie = "Onc";
    public static final String slncLie = "Slnc";
    public static final String spncLie = "Spnc";
    public static final String algebraNamePrefix = "Линейный код ассоцированный с присоединённым представлением для : \"";
    public static final String hMatrixPrefix = "-----------------Матрицы  h-----------------\n\n";
    public static final String specialMatrixPrefix = "-----------------Специальные Матрицы  -----------------\n\n";
    public static final String aVectorsPrefix = "-----------------Векторы а-----------------\n\nВсего векторов A :";
    public static final String bVectorsPrefix = "-----------------Векторы b-----------------\n\nВсего векторов B :";
    public static final String combinationsPrefix = "-----------------Перестановки для линейных комбинаций векторов-----------------\n\nВсего перестановок :";
    @Inject
    private OncLieService oncLieService;
    @Inject
    private MatricesService matricesService;
    @Inject
    private GeomVectorService geomVectorService;
    @Inject
    private SlncLieService slncLieService;
    @Inject
    private SpncLieService spncLieService;
    @Inject
    private LinearSpaceService linearSpaceService;

    @Override
    public List<Map<String, String>> getReport(String algebraType, Number nDim, Number nModP) {
        int dim = nDim.intValue();
        int modP = nModP.intValue();
        switch (algebraType) {
            case oncLie:
                return getOncLieReport(dim, modP);
            case slncLie:
                return getSlncLieReport(dim, modP);
            case spncLie:
                return getSpncLieReport(dim, modP);
        }
        return null;
    }

    private List<Map<String, String>> getOncLieReport(int dim, int modP) {
        ArrayList<int[][]> h = oncLieService.generateMatricesH(dim);
        ArrayList<int[][]> f = oncLieService.generateSpecificMatrices(dim);
        ArrayList<GeomVector> vectorA = oncLieService.generateVectorsA(h, f);
        ArrayList<GeomVector> vectorB = oncLieService.generateVectorsB(vectorA, modP);
        String algebra = oncLieService.getAlgebraName();

        return generateReport(algebra,h,f,vectorA,vectorB,modP);
    }

    private List<Map<String, String>> getSlncLieReport(int dim, int modP) {
        ArrayList<int[][]> h = slncLieService.generateMatricesH(dim);
        ArrayList<int[][]> f = slncLieService.generateSpecificMatrices(dim);
        ArrayList<GeomVector> vectorA = slncLieService.generateVectorsA(h, f);
        ArrayList<GeomVector> vectorB = slncLieService.generateVectorsB(vectorA, modP);
        String algebra = oncLieService.getAlgebraName();

        return generateReport(algebra,h,f,vectorA,vectorB,modP);
    }

    private List<Map<String, String>> getSpncLieReport(int dim, int modP) {
        ArrayList<int[][]> h = spncLieService.generateMatricesH(dim);
        ArrayList<int[][]> f = spncLieService.generateSpecificMatrices(dim);
        ArrayList<GeomVector> vectorsA = spncLieService.generateVectorsA(h, f);
        ArrayList<GeomVector> vectorsB = spncLieService.generateVectorsB(vectorsA, modP);
        String algebra = spncLieService.getAlgebraName();
        return generateReport(algebra,h,f,vectorsA,vectorsB,modP);
    }

    private List<Map<String, String>> generateReport(String algebra,ArrayList<int[][]> h,ArrayList<int[][]> f,ArrayList<GeomVector> vectorsA,ArrayList<GeomVector> vectorsB, int modP){
        List<Map<String, String>> report = new LinkedList<>();
        Map<String, String> reportMap = new HashMap<>();
        String algebraName = algebraNamePrefix + algebra;
        String hMatrix = hMatrixPrefix + matricesService.getMatrixListStringView(h);
        String specialMatrix = specialMatrixPrefix + matricesService.getMatrixListStringView(f);
        String aVectors = aVectorsPrefix + vectorsA.size() + "\n\n" + geomVectorService.getStringView(vectorsA);
        String bVectors = bVectorsPrefix + vectorsB.size() + "\n\n" + geomVectorService.getStringView(vectorsB);
        List<ArrayList<Integer>> combinations = linearSpaceService.generateConstants(vectorsB.size(), modP);
        String lineVectors = combinationsPrefix + combinations.size() + combinations;
        String vectorsAndCombinationMultiplyAndSum = getVectorsAndCombinationMultiplyAndSum(combinations,vectorsB,modP);
        Set<GeomVector> linearSpace = linearSpaceService.generateLinearSpace(combinations,vectorsB,modP);// вот тут странно п.с у дена в коде по другому
        String linerSpace = "Линейная оболочка из " + linearSpace.size() + " векторов: " + linearSpace;
        String distance = "Минимальное Кодовое расстояние : " + linearSpaceService.calculateDistance(linearSpace);
        reportMap.put("algebraName", algebraName);
        reportMap.put("hMatrix", hMatrix);
        reportMap.put("specialMatrix", specialMatrix);
        reportMap.put("aVectors", aVectors);
        reportMap.put("bVectors", bVectors);
        reportMap.put("lineVectors", lineVectors);
        reportMap.put("vectorsAndCombinationMultiplyAndSum", vectorsAndCombinationMultiplyAndSum);
        reportMap.put("linerSpace", linerSpace);
        reportMap.put("distance", distance);
        report.add(reportMap);
        return report;
    }

    private String getVectorsAndCombinationMultiplyAndSum(List<ArrayList<Integer>> combinations, ArrayList<GeomVector> vectorsB, int modP){
        StringBuilder result = new StringBuilder();
        for (ArrayList<Integer> subList : combinations) {
            ArrayList<GeomVector> newVectors = new ArrayList<>();
            for (int j = 0; j < vectorsB.size(); j++) {
                newVectors.add(GeomVector.multiplyByNumber(modP, vectorsB.get(j), subList.get(j)));
                result.append(subList.get(j)).append("*").append(vectorsB.get(j)).append("=").append(GeomVector.multiplyByNumber(modP, vectorsB.get(j), subList.get(j))).append("\n");
            }
            result.append("Сумма этих векторов: ").append(GeomVector.sumVectors(modP, newVectors)).append("\n");
        }
        return String.valueOf(result);
    }

}