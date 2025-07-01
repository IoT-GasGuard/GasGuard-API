package com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages;

public record LightingMessage(
    String deviceId,
    Integer value,
    Boolean automatic
){
}