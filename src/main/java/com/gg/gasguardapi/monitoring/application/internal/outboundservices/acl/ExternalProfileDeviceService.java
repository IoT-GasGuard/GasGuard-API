package com.gg.gasguardapi.monitoring.application.internal.outboundservices.acl;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileDeviceService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileDeviceService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Optional<Profiles> getProfileById(Long id){
        return profileContextFacade.getProfileById(id);
    }

    public void sendAlertToContacts(String location, Long profileId, String name, String status){
        profileContextFacade.sendSmsToEmergencyContacts(location, profileId, name, status);
    }
}
