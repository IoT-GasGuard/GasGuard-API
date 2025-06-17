package com.gg.gasguardapi.monitoring.interfaces.acl;

import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.domain.model.queries.GetByDeviceIdQuery;
import com.gg.gasguardapi.monitoring.domain.services.DeviceQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceContextFacade {
    private final DeviceQueryService deviceQueryService;

    public DeviceContextFacade(DeviceQueryService deviceQueryService) {
        this.deviceQueryService = deviceQueryService;
    }

    public Optional<Device> getDevice(String deviceId) {
        return deviceQueryService.handle(new GetByDeviceIdQuery(deviceId));
    }
}
