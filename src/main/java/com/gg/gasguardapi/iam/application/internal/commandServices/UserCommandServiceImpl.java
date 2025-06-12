package com.gg.gasguardapi.iam.application.internal.commandServices;

import com.gg.gasguardapi.iam.application.internal.outboundservices.acl.ExternalProfileService;
import com.gg.gasguardapi.iam.application.internal.outboundservices.hashing.HashingService;
import com.gg.gasguardapi.iam.application.internal.outboundservices.tokens.TokenService;
import com.gg.gasguardapi.iam.domain.model.aggregates.User;
import com.gg.gasguardapi.iam.domain.model.commands.SignInUserCommand;
import com.gg.gasguardapi.iam.domain.model.commands.SignUpUserCommand;
import com.gg.gasguardapi.iam.domain.services.UserCommandService;
import com.gg.gasguardapi.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final HashingService hashingService;

    private final ExternalProfileService externalProfileService;

    public UserCommandServiceImpl(
            UserRepository userRepository,
            TokenService tokenService,
            HashingService hashingService,
            ExternalProfileService externalProfileService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
        this.externalProfileService = externalProfileService;
    }

    private Optional<User> createUser(String email, String password) {
        if(userRepository.findByEmail(email).isPresent()){
            return Optional.empty();
        }
        var profile = externalProfileService.createProfile(email);
        if (profile.isEmpty())return Optional.empty();
        return Optional.of(new User(email,hashingService.encode(password),profile.get()));
    }

    @Override
    public Optional<User> handle(SignUpUserCommand command) {
        var user = createUser(command.email(), command.password());
        if (user.isEmpty())return Optional.empty();
        userRepository.save(user.get());
        return user;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInUserCommand command) {
        var user = userRepository.findByEmail(command.email());
        if (user.isEmpty())return Optional.empty();
        if (!hashingService.matches(command.password(), user.get().getPassword()))return Optional.empty();
        var token = tokenService.generateToken(user.get().getEmail());
        return Optional.of(ImmutablePair.of(user.get(),token));
    }
}
