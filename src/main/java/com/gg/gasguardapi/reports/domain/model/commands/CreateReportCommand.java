package com.gg.gasguardapi.reports.domain.model.commands;

import java.util.List;

public record CreateReportCommand(
        String reportId,
        String date,
        String time,
        String deviceId,
        Double gasLevel,
        String duration,
        List<Integer> actionsTaken,
        boolean resolved
) {
}
