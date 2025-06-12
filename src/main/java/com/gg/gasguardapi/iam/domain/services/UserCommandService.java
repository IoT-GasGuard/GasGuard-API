package com.gg.gasguardapi.iam.domain.services;

import com.gg.gasguardapi.iam.domain.model.aggregates.User;
import com.gg.gasguardapi.iam.domain.model.commands.SignInUserCommand;
import com.gg.gasguardapi.iam.domain.model.commands.SignUpUserCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(SignUpUserCommand command);
    Optional<ImmutablePair<User, String>> handle(SignInUserCommand command);
}
