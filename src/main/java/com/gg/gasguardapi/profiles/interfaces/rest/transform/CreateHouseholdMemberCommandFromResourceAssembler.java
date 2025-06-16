package com.gg.gasguardapi.profiles.interfaces.rest.transform;

import com.gg.gasguardapi.profiles.domain.model.commands.CreateHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.CreateHouseholdMemberResource;

public class CreateHouseholdMemberCommandFromResourceAssembler {
    public static CreateHouseholdMemberCommand toCommandFromResource(CreateHouseholdMemberResource resource) {
        return new CreateHouseholdMemberCommand(
                resource.profileId(),
                resource.name(),
                resource.email(),
                resource.phone(),
                resource.emergencyContact(),
                resource.gasAlerts()
        );
    }
}
