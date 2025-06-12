package com.gg.gasguardapi.monitoring.interfaces.rest.resources;

public record DeviceResource(
        Long id,
        String deviceId,
        String name,
        String status,
        String lastReading,
        String location
) {
}
