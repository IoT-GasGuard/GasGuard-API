package com.gg.gasguardapi.profiles.interfaces.acl;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.domain.model.commands.CreateProfileCommand;
import com.gg.gasguardapi.profiles.domain.services.ProfilesCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileContextFacade {
    private final ProfilesCommandService profilesCommandService;

    public ProfileContextFacade(ProfilesCommandService profilesCommandService) {
        this.profilesCommandService = profilesCommandService;
    }

    public Optional<Profiles> createProfile(String name, String email, String phoneNumber){
        return profilesCommandService.handle(new CreateProfileCommand(name, email, phoneNumber));
    }
}
