package com.gg.gasguardapi.profiles.domain.services;

import com.gg.gasguardapi.profiles.domain.model.commands.CreateHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.DeleteHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.SendSMSToEmergencyContactCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.UpdateHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.domain.model.entities.HouseholdMember;

import java.util.Optional;

public interface HouseholdMemberCommandService {
    Optional<HouseholdMember> handle(CreateHouseholdMemberCommand command);
    Optional<HouseholdMember> handle(UpdateHouseholdMemberCommand command);
    Optional<Long> handle(DeleteHouseholdMemberCommand command);
    void handle(SendSMSToEmergencyContactCommand command);
}
