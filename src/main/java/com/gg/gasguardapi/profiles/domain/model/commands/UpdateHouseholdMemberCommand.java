package com.gg.gasguardapi.profiles.domain.model.commands;

public record UpdateHouseholdMemberCommand(
        Long id,
        String name,
        String email,
        String phone,
        boolean emergencyContact,
        boolean gasAlerts
) {
}
