package com.gg.gasguardapi.profiles.interfaces.rest.transform;

import com.gg.gasguardapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(UpdateProfileResource resource, Long id) {
        return new UpdateProfileCommand(
                id,
                resource.name(),
                resource.phoneNumber()
        );
    }
}
