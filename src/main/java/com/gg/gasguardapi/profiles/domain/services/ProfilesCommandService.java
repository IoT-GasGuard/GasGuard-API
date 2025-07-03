package com.gg.gasguardapi.profiles.domain.services;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.domain.model.commands.CreateProfileCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfilesCommandService {
    Optional<Profiles> handle(CreateProfileCommand command);
    Optional<Profiles> handle(UpdateProfileCommand command);
}
