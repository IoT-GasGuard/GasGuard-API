package com.gg.gasguardapi.profiles.domain.model.commands;

public record CreateHouseholdMemberCommand(
        Long profileId,
        String name,
        String email,
        String phone,
        boolean emergencyContact,
        boolean gasAlerts
) {
}
