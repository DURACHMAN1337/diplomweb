package com.company.diplomweb.listaners;

import com.haulmont.cuba.core.global.Resources;
import com.haulmont.cuba.core.sys.events.AppContextStartedEvent;
import com.haulmont.cuba.security.app.Authenticated;
import com.haulmont.reports.ReportImportExportAPI;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.InputStream;
import java.util.Objects;

@Component(ReportImportListener.NAME)
public class ReportImportListener {
    public static final String NAME = "marketbench_ReportImportListener";
    private static final String PATH_TO_REPORTS = "/com/company/diplomweb/data/Report.zip";
    @Inject
    private ReportImportExportAPI reportImportExportAPI;
    @Inject
    private Logger log;
    @Inject
    private Resources resources;

    @Authenticated
    @EventListener
    public void applicationContextStarted(AppContextStartedEvent event) {
        try {
            byte[] reportsZip;
            try (InputStream is = resources.getResourceAsStream(PATH_TO_REPORTS)) {
                Objects.requireNonNull(is);
                reportsZip = IOUtils.toByteArray(is);
            }
            reportImportExportAPI.importReports(reportsZip);
        } catch (Exception e) {
            log.error("Failed to import reports on server start", e);
        }
    }
}

