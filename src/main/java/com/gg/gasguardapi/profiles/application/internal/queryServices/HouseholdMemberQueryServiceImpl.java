package com.gg.gasguardapi.profiles.application.internal.queryServices;

import com.gg.gasguardapi.profiles.domain.model.entities.HouseholdMember;
import com.gg.gasguardapi.profiles.domain.model.queries.GetAllHouseholdMemberByProfileId;
import com.gg.gasguardapi.profiles.domain.services.HouseholdMemberQueryService;
import com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories.HouseholdMemberRepository;
import com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HouseholdMemberQueryServiceImpl implements HouseholdMemberQueryService {
    private final HouseholdMemberRepository householdMemberRepository;
    private final ProfilesRepository profilesRepository;

    public HouseholdMemberQueryServiceImpl(
            HouseholdMemberRepository householdMemberRepository,
            ProfilesRepository profilesRepository) {
        this.householdMemberRepository = householdMemberRepository;
        this.profilesRepository = profilesRepository;
    }

    @Override
    public List<HouseholdMember> handle(GetAllHouseholdMemberByProfileId query) {
        var profile = profilesRepository.findById(query.profileId());
        if (profile.isEmpty())return Collections.emptyList();
        return householdMemberRepository.findAllByProfileId(query.profileId());

    }
}
