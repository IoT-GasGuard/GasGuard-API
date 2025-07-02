package com.gg.gasguardapi.profiles.domain.model.commands;

public record UpdateProfileCommand(
        Long id,
        String name,
        String phoneNumber
) {
}
