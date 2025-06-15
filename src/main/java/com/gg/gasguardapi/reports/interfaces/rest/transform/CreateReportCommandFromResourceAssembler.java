package com.gg.gasguardapi.reports.interfaces.rest.transform;

import com.gg.gasguardapi.reports.domain.model.commands.CreateReportCommand;
import com.gg.gasguardapi.reports.interfaces.rest.resources.CreateReportResource;

public class CreateReportCommandFromResourceAssembler {
    public static CreateReportCommand toCommandFromResource(CreateReportResource resource) {
        return new CreateReportCommand(
                resource.reportId(),
                resource.date(),
                resource.time(),
                resource.deviceId(),
                resource.gasLevel(),
                resource.duration(),
                resource.actions(),
                resource.resolved()

        );
    }
}
