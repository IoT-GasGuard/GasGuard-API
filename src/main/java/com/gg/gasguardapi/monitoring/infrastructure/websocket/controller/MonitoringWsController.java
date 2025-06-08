package com.gg.gasguardapi.monitoring.infrastructure.websocket.controller;

import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages.GasReadingMessage;
import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.messages.GasUpdateMessage;
import com.gg.gasguardapi.monitoring.infrastructure.websocket.controller.transform.GasUpdateMessageFromReadingAssembler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MonitoringWsController {

    @MessageMapping("/gas")
    @SendTo("/topic/gas")
    public GasUpdateMessage handleGasReading(GasReadingMessage gasReadingMessage) {

        String message = gasReadingMessage.ppm() > 0.683761 ?
                "ALERTA Nivel De Gas Peligroso: "+ gasReadingMessage.ppm()+" ppm"
                : "Lectura de gas: " + gasReadingMessage.ppm()+" ppm";

        var resource = GasUpdateMessageFromReadingAssembler.handle(gasReadingMessage,message);
        return resource;
    }
}
