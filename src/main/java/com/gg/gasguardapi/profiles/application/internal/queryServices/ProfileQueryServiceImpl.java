package com.gg.gasguardapi.profiles.application.internal.queryServices;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.domain.model.queries.GetProfileByIdQuery;
import com.gg.gasguardapi.profiles.domain.services.ProfilesQueryService;
import com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfilesQueryService {
    private final ProfilesRepository profilesRepository;

    public ProfileQueryServiceImpl(ProfilesRepository profilesRepository) {
        this.profilesRepository = profilesRepository;
    }

    @Override
    public Optional<Profiles> handle(GetProfileByIdQuery query) {
        return profilesRepository.findById(query.id());
    }
}
