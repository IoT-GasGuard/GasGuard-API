package com.gg.gasguardapi.profiles.domain.model.commands;

public record SendSMSToEmergencyContactCommand(
        String location,
        Long profileId,
        String name,
        String status
) {
}
