package com.gg.gasguardapi.monitoring.domain.model.commands;

public record SendAlertToContactsCommand(
        String deviceId,
        String status
) {
}
