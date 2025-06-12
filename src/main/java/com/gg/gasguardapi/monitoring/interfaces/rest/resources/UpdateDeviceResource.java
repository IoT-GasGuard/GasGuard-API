package com.gg.gasguardapi.monitoring.interfaces.rest.resources;

public record UpdateDeviceResource(
        String deviceId,
        String name,
        String location
) {
}
