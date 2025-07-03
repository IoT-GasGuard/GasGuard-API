package com.gg.gasguardapi.profiles.application.internal.commandServices;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.domain.model.commands.CreateProfileCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.UpdateProfileCommand;
import com.gg.gasguardapi.profiles.domain.services.ProfilesCommandService;
import com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfilesCommandServiceImpl implements ProfilesCommandService {
    private final ProfilesRepository profilesRepository;

    public ProfilesCommandServiceImpl(ProfilesRepository profilesRepository) {
        this.profilesRepository = profilesRepository;
    }

    @Override
    public Optional<Profiles> handle(CreateProfileCommand command) {
        var profile = new Profiles(command);
        profilesRepository.save(profile);
        return Optional.of(profile);
    }

    @Override
    public Optional<Profiles> handle(UpdateProfileCommand command) {
        var profile = profilesRepository.findById(command.id());
        if (profile.isEmpty())return Optional.empty();
        profile.get().setName(command.name());
        profile.get().setPhoneNumber(command.phoneNumber());
        profilesRepository.save(profile.get());
        return profile;
    }
}
