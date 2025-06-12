package com.gg.gasguardapi.monitoring.application.internal.commandServices;

import com.gg.gasguardapi.monitoring.application.internal.outboundservices.acl.ExternalProfileDeviceService;
import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.domain.model.commands.CreateDeviceCommand;
import com.gg.gasguardapi.monitoring.domain.services.DeviceCommandService;
import com.gg.gasguardapi.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceCommandServiceImpl implements DeviceCommandService {
    private final DeviceRepository deviceRepository;
    private final ExternalProfileDeviceService externalProfileDeviceService;

    public DeviceCommandServiceImpl(
            DeviceRepository deviceRepository,
            ExternalProfileDeviceService externalProfileDeviceService) {
        this.deviceRepository = deviceRepository;
        this.externalProfileDeviceService = externalProfileDeviceService;
    }

    @Override
    public Optional<Device> handle(CreateDeviceCommand command) {
        var profile = externalProfileDeviceService.getProfileById(command.profileId());
        if (profile.isEmpty())return Optional.empty();
        var device = new Device(command, profile.get());
        deviceRepository.save(device);
        return Optional.of(device);
    }
}
