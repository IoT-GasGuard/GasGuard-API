package com.gg.gasguardapi.profiles.application.internal.outboundservices.acl;

import com.gg.gasguardapi.iam.infrastructure.authorization.sfs.pipeline.UnauthorizedRequestHandlerEntryPoint;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Value("${twilio.phone-number}")
    private String fromNumber;
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    public void sendSMS(String to, String message) {
        try{
            Message.creator(
                    new PhoneNumber(to),
                    new PhoneNumber(fromNumber),
                    message
            ).create();
        }catch (Exception e){
            LOGGER.error("Failed to send SMS to phone number: "+to+". Error: ",e.getMessage());
        }


    }
}
