package com.gg.gasguardapi.reports.infrastructure.persistence.jpa.repositories;

import com.gg.gasguardapi.reports.domain.model.aggregates.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportsRepository extends JpaRepository<Report, Long> {
    Boolean existsByReportId(String reportId);
    List<Report>findAllByDeviceId(Long deviceId);
}
