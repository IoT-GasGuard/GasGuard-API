package com.gg.gasguardapi.monitoring.interfaces.rest.transform;

import com.gg.gasguardapi.monitoring.domain.model.commands.CreateDeviceCommand;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.CreateDeviceResource;

public class CreateDeviceCommandFromResourceAssembler {
    public static CreateDeviceCommand toCommandFromResource(CreateDeviceResource resource) {
        return new CreateDeviceCommand(
                resource.deviceId(),
                resource.name(),
                resource.location(),
                resource.profileId()
        );
    }
}
