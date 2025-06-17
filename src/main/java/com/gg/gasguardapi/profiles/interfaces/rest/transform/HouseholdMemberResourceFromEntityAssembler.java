package com.gg.gasguardapi.profiles.interfaces.rest.transform;

import com.gg.gasguardapi.profiles.domain.model.entities.HouseholdMember;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.HouseholdMemberResource;

public class HouseholdMemberResourceFromEntityAssembler {
    public static HouseholdMemberResource toResourceFromEntity(HouseholdMember entity) {
        return new HouseholdMemberResource(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.isEmergencyContact(),
                entity.isGasAlerts(),
                entity.getProfile().getId()
        );
    }
}
