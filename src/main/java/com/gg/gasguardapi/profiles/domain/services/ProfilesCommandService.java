package com.gg.gasguardapi.profiles.domain.services;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfilesCommandService {
    Optional<Profiles> handle(CreateProfileCommand command);
}
