package com.gg.gasguardapi.profiles.interfaces.acl;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.domain.model.commands.CreateProfileCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.SendSMSToEmergencyContactCommand;
import com.gg.gasguardapi.profiles.domain.model.queries.GetProfileByIdQuery;
import com.gg.gasguardapi.profiles.domain.services.HouseholdMemberCommandService;
import com.gg.gasguardapi.profiles.domain.services.ProfilesCommandService;
import com.gg.gasguardapi.profiles.domain.services.ProfilesQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileContextFacade {
    private final ProfilesCommandService profilesCommandService;
    private final ProfilesQueryService profilesQueryService;
    private final HouseholdMemberCommandService householdMemberCommandService;

    public ProfileContextFacade(
            ProfilesCommandService profilesCommandService,
            ProfilesQueryService profilesQueryService,
            HouseholdMemberCommandService householdMemberCommandService) {
        this.profilesCommandService = profilesCommandService;
        this.profilesQueryService = profilesQueryService;
        this.householdMemberCommandService = householdMemberCommandService;
    }

    public Optional<Profiles> createProfile(String name, String email, String phoneNumber){
        return profilesCommandService.handle(new CreateProfileCommand(name, email, phoneNumber));
    }

    public Optional<Profiles> getProfileById(Long id){
        return profilesQueryService.handle(new GetProfileByIdQuery(id));
    }

    public void sendSmsToEmergencyContacts(String location, Long profileId, String name, String status){
        householdMemberCommandService.handle(new SendSMSToEmergencyContactCommand(location, profileId, name, status));
    }
}
