package com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.transform;

import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages.GasReadingMessage;
import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages.GasUpdateMessage;

public class GasUpdateMessageFromReadingAssembler {
    public static GasUpdateMessage handle(GasReadingMessage gasReading, String message, Double value){
        return new GasUpdateMessage(
                gasReading.deviceId(),
                gasReading.ppm(),
                gasReading.timestamp(),
                gasReading.status(),
                message,
                value
        );
    }
}
