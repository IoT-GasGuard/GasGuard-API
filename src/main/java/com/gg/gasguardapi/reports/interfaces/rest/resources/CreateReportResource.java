package com.gg.gasguardapi.reports.interfaces.rest.resources;

import java.util.List;

public record CreateReportResource(
        String reportId,
        String date,
        String time,
        String deviceId,
        Double gasLevel,
        String duration,
        List<Integer> actions,
        boolean resolved
) {
}
