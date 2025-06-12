package com.gg.gasguardapi.monitoring.domain.services;

import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.domain.model.commands.CreateDeviceCommand;
import com.gg.gasguardapi.monitoring.domain.model.commands.DeleteDeviceCommand;
import com.gg.gasguardapi.monitoring.domain.model.commands.UpdateDeviceCommand;

import java.util.Optional;

public interface DeviceCommandService {
    Optional<Device> handle(CreateDeviceCommand command);
    Optional<Device> handle(UpdateDeviceCommand command);
    Optional<String> handle(DeleteDeviceCommand command);
}
