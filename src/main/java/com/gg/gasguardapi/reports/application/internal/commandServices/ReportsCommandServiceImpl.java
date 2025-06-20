package com.gg.gasguardapi.reports.application.internal.commandServices;

import com.gg.gasguardapi.reports.application.internal.outboundservices.acl.ExternalDeviceReportService;
import com.gg.gasguardapi.reports.domain.model.aggregates.Report;
import com.gg.gasguardapi.reports.domain.model.commands.CreateReportCommand;
import com.gg.gasguardapi.reports.domain.model.valueObjects.Protocols;
import com.gg.gasguardapi.reports.domain.services.ReportsCommandService;
import com.gg.gasguardapi.reports.infrastructure.persistence.jpa.repositories.ReportsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReportsCommandServiceImpl implements ReportsCommandService {
    private final ReportsRepository reportsRepository;
    private final ExternalDeviceReportService externalDeviceReportService;

    public ReportsCommandServiceImpl(
            ReportsRepository reportsRepository,
            ExternalDeviceReportService externalDeviceReportService) {
        this.reportsRepository = reportsRepository;
        this.externalDeviceReportService = externalDeviceReportService;
    }

    private Set<Protocols> mapIdsToProtocols(List<Integer> ids){
        return ids.stream()
                .map(id-> Protocols.values()[id-1])
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Report> handle(CreateReportCommand command) {
        if (reportsRepository.existsByReportId(command.reportId())){
            return Optional.empty();
        }

        int enumSize = Protocols.values().length;
        if (!command.actionsTaken().stream().allMatch(id->id>=1 && id<=enumSize)){
            return Optional.empty();
        }

        var protocols = mapIdsToProtocols(command.actionsTaken());

        var device = externalDeviceReportService.getDevice(command.deviceId());
        if (device.isEmpty())return Optional.empty();

        var report = new Report(command,device.get(),device.get().getProfiles(),protocols);

        reportsRepository.save(report);

        return Optional.of(report);
    }
}
