package com.gg.gasguardapi.iam.interfaces.rest.transform;

import com.gg.gasguardapi.iam.domain.model.aggregates.User;
import com.gg.gasguardapi.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(entity.getId(), entity.getEmail());

    }
}
