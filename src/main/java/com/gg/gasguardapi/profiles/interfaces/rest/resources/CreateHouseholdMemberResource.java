package com.gg.gasguardapi.profiles.interfaces.rest.resources;

public record CreateHouseholdMemberResource (
        Long profileId,
        String name,
        String email,
        String phone,
        boolean emergencyContact,
        boolean gasAlerts
) {
}
