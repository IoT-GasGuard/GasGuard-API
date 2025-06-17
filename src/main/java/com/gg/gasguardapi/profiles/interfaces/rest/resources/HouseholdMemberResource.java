package com.gg.gasguardapi.profiles.interfaces.rest.resources;

public record HouseholdMemberResource(
        Long id,
        String name,
        String email,
        String phone,
        boolean emergencyContact,
        boolean gasAlerts,
        Long profileId
) {
}
