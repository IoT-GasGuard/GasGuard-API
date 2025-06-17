package com.gg.gasguardapi.iam.domain.model.commands;

public record SignUpUserCommand(
        String email,
        String password
) {
}
