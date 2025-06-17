package com.gg.gasguardapi.profiles.domain.services;

import com.gg.gasguardapi.profiles.domain.model.entities.HouseholdMember;
import com.gg.gasguardapi.profiles.domain.model.queries.GetAllHouseholdMemberByProfileId;

import java.util.List;

public interface HouseholdMemberQueryService {
    List<HouseholdMember> handle(GetAllHouseholdMemberByProfileId query);
}
