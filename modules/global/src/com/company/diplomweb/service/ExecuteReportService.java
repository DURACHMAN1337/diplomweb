package com.company.diplomweb.service;

import com.haulmont.cuba.core.entity.FileDescriptor;

public interface ExecuteReportService {
    String NAME = "diplomweb_ExecuteReportService";
    FileDescriptor execute(String algebraType, Number dim, Number modP);
}