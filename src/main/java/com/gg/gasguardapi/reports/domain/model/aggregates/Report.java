package com.gg.gasguardapi.reports.domain.model.aggregates;

import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.reports.domain.model.commands.CreateReportCommand;
import com.gg.gasguardapi.reports.domain.model.valueObjects.Protocols;
import com.gg.gasguardapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Report extends AuditableAbstractAggregateRoot<Report> {
    private String reportId;
    private String date;
    private String time;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "device_id")
    private Device device;
    private String location;
    private Double gasLevel;
    private String duration;
    @ElementCollection(targetClass = Protocols.class,fetch = FetchType.EAGER)
    @CollectionTable(
            name = "security_protocols",
            joinColumns = @JoinColumn(name = "report_id")
    )
    @Enumerated(EnumType.STRING)
    private Set<Protocols> actionsTaken = new HashSet<>();
    private boolean resolved;

    public Report() {
    }

    public Report(CreateReportCommand command, Device device, Set<Protocols> actionsTaken) {
        this.reportId=command.reportId();
        this.date = command.date();
        this.time = command.time();
        this.device = device;
        this.location = device.getLocation();
        this.gasLevel = command.gasLevel();
        this.duration = command.duration();
        this.actionsTaken = actionsTaken;
        this.resolved = command.resolved();
    }

    public String getReportId() {
        return reportId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Device getDevice() {
        return device;
    }

    public String getLocation() {
        return location;
    }

    public Double getGasLevel() {
        return gasLevel;
    }

    public String getDuration() {
        return duration;
    }

    public Set<Protocols> getActionsTaken() {
        return actionsTaken;
    }

    public boolean isResolved() {
        return resolved;
    }
}
