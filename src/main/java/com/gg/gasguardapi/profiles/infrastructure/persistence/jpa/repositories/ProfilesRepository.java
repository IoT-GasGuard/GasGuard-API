package com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepository extends JpaRepository<Profiles,Long> {
}
