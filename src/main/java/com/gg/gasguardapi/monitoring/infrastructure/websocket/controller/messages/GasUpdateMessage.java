package com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages;

import java.util.Date;

public record GasUpdateMessage (
        String deviceId,
        Double ppm,
        Date timestamp,
        String status,
        String message
) {
}
