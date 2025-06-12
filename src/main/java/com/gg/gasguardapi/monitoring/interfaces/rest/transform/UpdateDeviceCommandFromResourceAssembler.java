package com.gg.gasguardapi.monitoring.interfaces.rest.transform;

import com.gg.gasguardapi.monitoring.domain.model.commands.UpdateDeviceCommand;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.UpdateDeviceResource;

public class UpdateDeviceCommandFromResourceAssembler {
    public static UpdateDeviceCommand toCommandFromResource(Long id, UpdateDeviceResource resource){
        return new UpdateDeviceCommand(
                id,
                resource.deviceId(),
                resource.name(),
                resource.location()
        );
    }
}
