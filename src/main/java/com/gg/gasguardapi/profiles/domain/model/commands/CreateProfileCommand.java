package com.gg.gasguardapi.profiles.domain.model.commands;

public record CreateProfileCommand(
        String name,
        String email,
        String phoneNumber
) {
}
