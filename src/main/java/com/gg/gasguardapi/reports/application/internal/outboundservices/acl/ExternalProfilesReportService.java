package com.gg.gasguardapi.reports.application.internal.outboundservices.acl;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfilesReportService {
    private final ProfileContextFacade profileContextFacade;

    public ExternalProfilesReportService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Optional<Profiles> getProfileById(Long profileId){
        return profileContextFacade.getProfileById(profileId);
    }
}
