package com.gg.gasguardapi.reports.domain.services;

import com.gg.gasguardapi.reports.domain.model.aggregates.Report;
import com.gg.gasguardapi.reports.domain.model.commands.CreateReportCommand;

import java.util.Optional;

public interface ReportsCommandService {
    Optional<Report> handle(CreateReportCommand command);
}
