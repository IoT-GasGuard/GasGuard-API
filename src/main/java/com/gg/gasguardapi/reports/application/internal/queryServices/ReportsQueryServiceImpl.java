package com.gg.gasguardapi.reports.application.internal.queryServices;

import com.gg.gasguardapi.reports.application.internal.outboundservices.acl.ExternalProfilesReportService;
import com.gg.gasguardapi.reports.domain.model.aggregates.Report;
import com.gg.gasguardapi.reports.domain.model.queries.GetAllReportsByProfileId;
import com.gg.gasguardapi.reports.domain.services.ReportsQueryService;
import com.gg.gasguardapi.reports.infrastructure.persistence.jpa.repositories.ReportsRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReportsQueryServiceImpl implements ReportsQueryService{
    private final ReportsRepository reportsRepository;
    //private final ExternalDeviceReportService externalDeviceReportService;
    private final ExternalProfilesReportService externalProfilesReportService;

    public ReportsQueryServiceImpl(
            ReportsRepository reportsRepository,
            //ExternalDeviceReportService externalDeviceReportService,
            ExternalProfilesReportService externalProfilesReportService) {
        this.reportsRepository = reportsRepository;
        //this.externalDeviceReportService = externalDeviceReportService;
        this.externalProfilesReportService = externalProfilesReportService;
    }

    /*@Override
    public List<Report> handle(GetReportsByDeviceId query) {
        var device = externalDeviceReportService.getDevice(query.deviceId());
        if (device.isEmpty())return Collections.emptyList();
        return reportsRepository.findAllByDeviceId(device.get().getId());
    }*/

    @Override
    public List<Report> handle(GetAllReportsByProfileId query) {
        var profile =externalProfilesReportService.getProfileById(query.profileId());
        if (profile.isEmpty())return Collections.emptyList();
        return reportsRepository.findAllByProfilesId(profile.get().getId());
    }
}