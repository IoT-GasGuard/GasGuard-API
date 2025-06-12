package com.gg.gasguardapi.monitoring.application.internal.queryServices;

import com.gg.gasguardapi.monitoring.application.internal.outboundservices.acl.ExternalProfileDeviceService;
import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.domain.model.queries.GetAllDevicesByProfileId;
import com.gg.gasguardapi.monitoring.domain.services.DeviceQueryService;
import com.gg.gasguardapi.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DeviceQueryServiceImpl implements DeviceQueryService {
    private final DeviceRepository deviceRepository;
    private final ExternalProfileDeviceService externalProfileDeviceService;

    public DeviceQueryServiceImpl(
            DeviceRepository deviceRepository,
            ExternalProfileDeviceService externalProfileDeviceService) {
        this.deviceRepository = deviceRepository;
        this.externalProfileDeviceService = externalProfileDeviceService;
    }

    @Override
    public List<Device> handle(GetAllDevicesByProfileId query) {
        var profile = externalProfileDeviceService.getProfileById(query.profileId());
        if (profile.isEmpty())return Collections.emptyList();
        return deviceRepository.findAllByProfilesId(profile.get().getId());
    }
}
