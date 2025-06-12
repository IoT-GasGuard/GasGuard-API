package com.gg.gasguardapi.profiles.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String name,
        String email,
        String phoneNumber
) {
}
