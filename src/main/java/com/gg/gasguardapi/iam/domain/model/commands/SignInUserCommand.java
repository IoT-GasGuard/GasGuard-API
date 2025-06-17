package com.gg.gasguardapi.iam.domain.model.commands;

public record SignInUserCommand(
        String email,
        String password
) {
}
