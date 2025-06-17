package com.gg.gasguardapi.profiles.domain.services;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.Optional;

public interface ProfilesQueryService {
    Optional<Profiles> handle(GetProfileByIdQuery query);
}
