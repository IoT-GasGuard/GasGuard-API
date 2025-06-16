package com.gg.gasguardapi.profiles.application.internal.commandServices;

import com.gg.gasguardapi.profiles.domain.model.commands.CreateHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.domain.model.entities.HouseholdMember;
import com.gg.gasguardapi.profiles.domain.services.HouseholdMemberCommandService;
import com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories.HouseholdMemberRepository;
import com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseholdMemberCommandServiceImpl implements HouseholdMemberCommandService {
    private final HouseholdMemberRepository householdMemberRepository;
    private final ProfilesRepository profilesRepository;

    public HouseholdMemberCommandServiceImpl(
            HouseholdMemberRepository householdMemberRepository,
            ProfilesRepository profilesRepository) {
        this.householdMemberRepository = householdMemberRepository;
        this.profilesRepository = profilesRepository;
    }

    @Override
    public Optional<HouseholdMember> handle(CreateHouseholdMemberCommand command) {
        var profile = profilesRepository.findById(command.profileId());
        if (profile.isEmpty())return Optional.empty();
        var householdMember = new HouseholdMember(command, profile.get());
        householdMemberRepository.save(householdMember);
        return Optional.of(householdMember);
    }
}
