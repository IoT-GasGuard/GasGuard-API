package com.gg.gasguardapi.monitoring.interfaces.rest.resources;

public record CreateDeviceResource(
        String deviceId,
        String name,
        String location,
        Long profileId
) {
}
