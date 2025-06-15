package com.gg.gasguardapi.reports.application.internal.outboundservices.mqtt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gg.gasguardapi.reports.domain.model.commands.CreateReportCommand;
import com.gg.gasguardapi.reports.domain.services.ReportsCommandService;
import com.gg.gasguardapi.reports.interfaces.rest.resources.CreateReportResource;
import com.gg.gasguardapi.reports.interfaces.rest.transform.CreateReportCommandFromResourceAssembler;
import com.gg.gasguardapi.reports.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqttReportSubscriber implements MqttCallback {

    @Value(value = "${mqttClient.brokerUrl}")
    private String brokerUrl;

    @Value(value = "${mqttClient.clientId}")
    private String clientId;

    private String topic = "gg/reports";

    private MqttClient client;

    private final ObjectMapper mapper = new ObjectMapper();
    private final ReportsCommandService reportsCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttReportSubscriber.class);

    public MqttReportSubscriber(ReportsCommandService reportsCommandService) {
        this.reportsCommandService = reportsCommandService;
    }

    @PostConstruct
    public void init(){
        try{
            client = new MqttClient(brokerUrl, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.setCallback(this);
            client.connect(options);
            client.subscribe(topic, 1);
            LOGGER.info("Subscribed to topic {}", topic);
        }catch (MqttException e){
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void connectionLost(Throwable throwable) {
        LOGGER.error(throwable.getMessage());
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        try {
            String json = new String(mqttMessage.getPayload());
            CreateReportResource resource = mapper.readValue(json, CreateReportResource.class);

            CreateReportCommand command = CreateReportCommandFromResourceAssembler.toCommandFromResource(resource);

            var report = reportsCommandService.handle(command);
            if (report.isEmpty()){
                LOGGER.error("Error during report creation");
                return;
            }
            var reportResource = ReportResourceFromEntityAssembler.toResourceFromEntity(report.get());
            LOGGER.info("Report resource created: {}", reportResource);

        } catch (Exception e) {
            System.err.println("Error al procesar mensaje MQTT: " + e.getMessage());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
