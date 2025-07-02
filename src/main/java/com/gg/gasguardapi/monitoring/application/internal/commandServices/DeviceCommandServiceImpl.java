package com.gg.gasguardapi.monitoring.application.internal.commandServices;

import com.gg.gasguardapi.monitoring.application.internal.outboundservices.acl.ExternalProfileDeviceService;
import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.domain.model.commands.CreateDeviceCommand;
import com.gg.gasguardapi.monitoring.domain.model.commands.DeleteDeviceCommand;
import com.gg.gasguardapi.monitoring.domain.model.commands.SendAlertToContactsCommand;
import com.gg.gasguardapi.monitoring.domain.model.commands.UpdateDeviceCommand;
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

    @Override
    public Optional<Device> handle(UpdateDeviceCommand command) {
        var device = deviceRepository.findById(command.id());
        if (device.isEmpty())return Optional.empty();
        device.get().setDeviceId(command.deviceId());
        device.get().setName(command.name());
        device.get().setLocation(command.location());
        deviceRepository.save(device.get());
        return device;
    }

    @Override
    public Optional<String> handle(DeleteDeviceCommand command) {
        var device = deviceRepository.findById(command.id());
        if (device.isEmpty())return Optional.empty();
        String deviceId = device.get().getDeviceId();
        deviceRepository.delete(device.get());
        return Optional.of(deviceId);
    }

    @Override
    public void handle(SendAlertToContactsCommand command) {
        var device = deviceRepository.findByDeviceId(command.deviceId());
        if (device.isEmpty())return;
        var profile = device.get().getProfiles();
        externalProfileDeviceService.sendAlertToContacts(device.get().getLocation());
    }
}
