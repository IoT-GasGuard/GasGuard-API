package com.gg.gasguardapi.monitoring.domain.model.aggregates;

import com.gg.gasguardapi.monitoring.domain.model.commands.CreateDeviceCommand;
import com.gg.gasguardapi.monitoring.domain.model.valueObjects.DeviceStatus;
import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import jakarta.persistence.*;

@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceStatus status;

    private String lastReading;
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profiles profiles;

    public Device() {}

    public Device(CreateDeviceCommand command, Profiles profile){
        this.deviceId=command.deviceId();
        this.name=command.name();
        this.status=DeviceStatus.ONLINE;
        this.lastReading="Just now";
        this.location=command.location();
        this.profiles=profile;
    }

    public Profiles getProfiles() {
        return profiles;
    }

    public Long getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public String getLastReading() {
        return lastReading;
    }
}
