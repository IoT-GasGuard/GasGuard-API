package com.gg.gasguardapi.monitoring.domain.model.commands;

public record UpdateDeviceCommand(
        Long id,
        String deviceId,
        String name,
        String location
) {
}
