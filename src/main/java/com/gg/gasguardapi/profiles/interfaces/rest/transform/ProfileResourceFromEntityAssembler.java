package com.gg.gasguardapi.profiles.interfaces.rest.transform;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.ProfileResource;


public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profiles entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhoneNumber()
        );
    }
}
