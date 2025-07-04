package com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages;

import java.util.Date;

public record GasReadingMessage(
        String deviceId,
        Double ppm,
        Double threshold,
        Date timestamp,
        String status
) {
}
