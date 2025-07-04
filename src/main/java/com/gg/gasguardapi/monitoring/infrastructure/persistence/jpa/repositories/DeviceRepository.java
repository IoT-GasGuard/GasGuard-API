package com.gg.gasguardapi.monitoring.infrastructure.persistence.jpa.repositories;

import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findAllByProfilesId(Long profileId);
    Optional<Device> findByDeviceId(String deviceId);
}
