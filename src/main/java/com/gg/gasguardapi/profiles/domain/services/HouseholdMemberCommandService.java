package com.gg.gasguardapi.profiles.domain.services;

import com.gg.gasguardapi.profiles.domain.model.commands.CreateHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.domain.model.entities.HouseholdMember;

import java.util.Optional;

public interface HouseholdMemberCommandService {
    Optional<HouseholdMember> handle(CreateHouseholdMemberCommand command);
}
