package com.gg.gasguardapi.monitoring.infrastructure.websocket.controller;

import com.gg.gasguardapi.monitoring.domain.model.commands.SendAlertToContactsCommand;
import com.gg.gasguardapi.monitoring.domain.services.DeviceCommandService;
import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages.GasReadingMessage;
import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages.LightingMessage;
import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.transform.GasUpdateMessageFromReadingAssembler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class MonitoringWsController {
    private final DeviceCommandService deviceCommandService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ConcurrentHashMap<String, String> lastStatusMap = new ConcurrentHashMap<>();

    public MonitoringWsController(
            DeviceCommandService deviceCommandService,
            SimpMessagingTemplate simpMessagingTemplate) {
        this.deviceCommandService = deviceCommandService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/gas")
    public void handleGasReading(GasReadingMessage gasReadingMessage) {
        String deviceId = gasReadingMessage.deviceId();
        String currentStatus = gasReadingMessage.status();
        String lastStatus = lastStatusMap.getOrDefault(deviceId, "NORMAL");

        // Solo enviamos SMS en las transiciones válidas
        if (
                ("NORMAL".equals(lastStatus) && ("WARNING".equals(currentStatus) || "ALERT".equals(currentStatus))) ||
                        ("WARNING".equals(lastStatus) && "ALERT".equals(currentStatus))
        ) {
            deviceCommandService.handle(new SendAlertToContactsCommand(deviceId, currentStatus));
            lastStatusMap.put(deviceId, currentStatus); // actualiza el nuevo estado
        }

        // Si vuelve a NORMAL, se permite futuras notificaciones
        if ("NORMAL".equals(currentStatus)) {
            lastStatusMap.put(deviceId, "NORMAL");
        }

        // WebSocket al frontend con mensaje de lectura
        Double value = gasReadingMessage.ppm() * 71.0;
        String message;
        if (gasReadingMessage.ppm() > gasReadingMessage.threshold()) {
            message = "ALERTA Nivel De Gas Peligroso: " + gasReadingMessage.ppm() + " ppm";
        } else {
            message = "Lectura de gas: " + gasReadingMessage.ppm() + " ppm";
        }

        var resource = GasUpdateMessageFromReadingAssembler.handle(
                gasReadingMessage, message, value
        );

        simpMessagingTemplate.convertAndSend("/topic/gas/" + resource.deviceId(), resource);
    }
    @MessageMapping("/lighting") // frontend: /app/lighting
    public void handleLightingMessage(LightingMessage lightingMessage){
        // Envia a: /topic/lighting/{deviceId}
        simpMessagingTemplate.convertAndSend("/topic/lighting/"+lightingMessage.deviceId(), lightingMessage);
    }
}
