package com.company.diplomweb.service.repor;

import java.util.List;
import java.util.Map;

public interface ReportWorker {
    String NAME = "diplomweb_ReportWorker";

    List<Map<String, String>> getReport(String algebraType, Number nDim, Number nModP);
}
