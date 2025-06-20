package com.gg.gasguardapi.profiles.interfaces.rest.transform;

import com.gg.gasguardapi.profiles.domain.model.commands.UpdateHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.UpdateHouseholdMemberResource;

public class UpdateHouseholdMemberCommandFromResourceAssembler {
    public static UpdateHouseholdMemberCommand toCommandFromResource(
            UpdateHouseholdMemberResource resource,
            Long contactId){
        return new UpdateHouseholdMemberCommand(
                contactId,
                resource.name(),
                resource.email(),
                resource.phone(),
                resource.emergencyContact(),
                resource.gasAlerts()
        );
    }
}
