package com.gg.gasguardapi.reports.interfaces.rest;

import com.gg.gasguardapi.reports.domain.model.queries.GetReportsByDeviceId;
import com.gg.gasguardapi.reports.domain.services.ReportsQueryService;
import com.gg.gasguardapi.reports.interfaces.rest.resources.ReportResource;
import com.gg.gasguardapi.reports.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportsController {
    private final ReportsQueryService reportsQueryService;

    public ReportsController(ReportsQueryService reportsQueryService) {
        this.reportsQueryService = reportsQueryService;
    }

    @GetMapping("/device/{deviceId}")
    public ResponseEntity<List<ReportResource>> getReportsByDeviceId(@PathVariable String deviceId) {
        var reports = reportsQueryService.handle(new GetReportsByDeviceId(deviceId));
        var resources= reports.stream()
                .map(ReportResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
