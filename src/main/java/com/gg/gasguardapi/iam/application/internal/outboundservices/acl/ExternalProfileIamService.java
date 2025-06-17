package com.gg.gasguardapi.iam.application.internal.outboundservices.acl;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileIamService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileIamService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Optional<Profiles> createProfile(String email){
        String name="";
        String phoneNumber="";
        return profileContextFacade.createProfile(name, email, phoneNumber);

    }
}
