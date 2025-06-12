package com.gg.gasguardapi.monitoring.domain.model.commands;

public record CreateDeviceCommand(
        String deviceId,
        String name,
        String location,
        Long profileId
) {
}
