package com.gg.gasguardapi.monitoring.infrastructure.websocket.controller;

import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages.GasReadingMessage;
import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.transform.GasUpdateMessageFromReadingAssembler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MonitoringWsController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public MonitoringWsController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/gas")
    public void handleGasReading(GasReadingMessage gasReadingMessage) {
        Double value = gasReadingMessage.ppm()*71.0;
        String message = "";
        if (gasReadingMessage.ppm()>gasReadingMessage.threshold()){
            message="ALERTA Nivel De Gas Peligroso: "+ gasReadingMessage.ppm()+" ppm";
        }else {
            message = "Lectura de gas: " + gasReadingMessage.ppm()+" ppm";
        }
        var resource = GasUpdateMessageFromReadingAssembler.handle(gasReadingMessage,message,value);
        simpMessagingTemplate.convertAndSend("/topic/gas/"+resource.deviceId(), resource);
    }
}
