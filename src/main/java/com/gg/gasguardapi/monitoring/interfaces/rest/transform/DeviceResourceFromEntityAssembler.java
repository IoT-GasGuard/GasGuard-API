package com.gg.gasguardapi.monitoring.interfaces.rest.transform;

import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.DeviceResource;

public class DeviceResourceFromEntityAssembler {
    public static DeviceResource toResourceFromEntity(Device entity){
        return new DeviceResource(
                entity.getId(),
                entity.getDeviceId(),
                entity.getName(),
                entity.getStatus().name(),
                entity.getLastReading(),
                entity.getLocation()
        );
    }
}
