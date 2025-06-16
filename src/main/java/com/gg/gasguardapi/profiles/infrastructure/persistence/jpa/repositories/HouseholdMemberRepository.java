package com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories;

import com.gg.gasguardapi.profiles.domain.model.entities.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
    List<HouseholdMember> findAllByProfileId(Long profileId);
}
