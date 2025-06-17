package com.gg.gasguardapi.iam.interfaces.rest.transform;

import com.gg.gasguardapi.iam.domain.model.aggregates.User;
import com.gg.gasguardapi.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User entity, String token){
        return new AuthenticatedUserResource(entity.getId(),entity.getEmail(),entity.getProfiles().getId(),token);

    }
}
