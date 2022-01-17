package com.company.diplomweb.service;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.reports.app.service.ReportService;
import com.haulmont.reports.entity.Report;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Service(ExecuteReportService.NAME)
public class ExecuteReportServiceBean implements ExecuteReportService {

    @Inject
    private DataManager dataManager;
    @Inject
    private ReportService reportService;


    @Override
    public FileDescriptor execute(String algebraType, Number dim, Number modP) {
        Map<String, Object> params = new HashMap<>();
        params.put("algebraType", algebraType);
        params.put("dim", dim);
        params.put("modP", modP);
        Report report = getReport();
        return reportService.createAndSaveReport(report, "DEFAULT", params, algebraType.replace("_n", "_" + dim) + "(mod " + modP + ")" + ".doc");
    }

    private Report getReport() {
        return dataManager.load(Report.class)
                .query("select r from report$Report r where r.name = :reportName")
                .view("report.view")
                .parameter("reportName", "Report")
                .one();
    }
}