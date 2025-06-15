package com.gg.gasguardapi.reports.domain.services;

import com.gg.gasguardapi.reports.domain.model.aggregates.Report;
import com.gg.gasguardapi.reports.domain.model.queries.GetReportsByDeviceId;

import java.util.List;

public interface ReportsQueryService {
    List<Report> handle(GetReportsByDeviceId query);
}
