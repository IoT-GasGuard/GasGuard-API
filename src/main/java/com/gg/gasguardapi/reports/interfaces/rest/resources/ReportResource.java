package com.gg.gasguardapi.reports.interfaces.rest.resources;

import com.gg.gasguardapi.reports.domain.model.valueObjects.Protocols;

import java.util.Set;

public record ReportResource(
        Long id,
        String date,
        String time,
        String device,
        String location,
        Double gasLevel,
        String duration,
        Set<Protocols> actionsTaken,
        boolean resolved
) {
}
