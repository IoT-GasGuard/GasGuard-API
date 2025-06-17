package com.gg.gasguardapi.reports.application.internal.queryServices;

import com.gg.gasguardapi.reports.application.internal.outboundservices.acl.ExternalDeviceReportService;
import com.gg.gasguardapi.reports.domain.model.aggregates.Report;
import com.gg.gasguardapi.reports.domain.model.queries.GetReportsByDeviceId;
import com.gg.gasguardapi.reports.domain.services.ReportsQueryService;
import com.gg.gasguardapi.reports.infrastructure.persistence.jpa.repositories.ReportsRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReportsQueryServiceImpl implements ReportsQueryService{
    private final ReportsRepository reportsRepository;
    private final ExternalDeviceReportService externalDeviceReportService;

    public ReportsQueryServiceImpl(
            ReportsRepository reportsRepository,
            ExternalDeviceReportService externalDeviceReportService) {
        this.reportsRepository = reportsRepository;
        this.externalDeviceReportService = externalDeviceReportService;
    }

    @Override
    public List<Report> handle(GetReportsByDeviceId query) {
        var device = externalDeviceReportService.getDevice(query.deviceId());
        if (device.isEmpty())return Collections.emptyList();
        return reportsRepository.findAllByDeviceId(device.get().getId());
    }
}