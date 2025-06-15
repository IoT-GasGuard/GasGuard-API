package com.gg.gasguardapi.reports.interfaces.rest.transform;

import com.gg.gasguardapi.reports.domain.model.aggregates.Report;
import com.gg.gasguardapi.reports.domain.model.valueObjects.Protocols;
import com.gg.gasguardapi.reports.interfaces.rest.resources.ReportResource;

public class ReportResourceFromEntityAssembler {
    public static ReportResource toResourceFromEntity(Report entity){
        return new ReportResource(
                entity.getId(),
                entity.getDate(),
                entity.getTime(),
                entity.getDevice().getName(),
                entity.getLocation(),
                entity.getGasLevel(),
                entity.getDuration(),
                entity.getActionsTaken(),
                entity.isResolved()

        );
    }
}
