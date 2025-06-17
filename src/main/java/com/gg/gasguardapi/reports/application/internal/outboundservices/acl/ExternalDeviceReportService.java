package com.gg.gasguardapi.reports.application.internal.outboundservices.acl;

import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.interfaces.acl.DeviceContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalDeviceReportService {
    private final DeviceContextFacade deviceContextFacade;

    public ExternalDeviceReportService(DeviceContextFacade deviceContextFacade) {
        this.deviceContextFacade = deviceContextFacade;
    }

    public Optional<Device> getDevice(String deviceId) {
        return deviceContextFacade.getDevice(deviceId);
    }
}
