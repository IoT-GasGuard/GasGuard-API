package com.gg.gasguardapi.profiles.application.internal.commandServices;

import com.gg.gasguardapi.profiles.application.internal.outboundservices.acl.SmsService;
import com.gg.gasguardapi.profiles.domain.model.commands.CreateHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.DeleteHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.SendSMSToEmergencyContactCommand;
import com.gg.gasguardapi.profiles.domain.model.commands.UpdateHouseholdMemberCommand;
import com.gg.gasguardapi.profiles.domain.model.entities.HouseholdMember;
import com.gg.gasguardapi.profiles.domain.services.HouseholdMemberCommandService;
import com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories.HouseholdMemberRepository;
import com.gg.gasguardapi.profiles.infrastructure.persistence.jpa.repositories.ProfilesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseholdMemberCommandServiceImpl implements HouseholdMemberCommandService {
    private final HouseholdMemberRepository householdMemberRepository;
    private final ProfilesRepository profilesRepository;

    private final SmsService smsService;

    public HouseholdMemberCommandServiceImpl(
            HouseholdMemberRepository householdMemberRepository,
            ProfilesRepository profilesRepository,
            SmsService smsService) {
        this.householdMemberRepository = householdMemberRepository;
        this.profilesRepository = profilesRepository;
        this.smsService = smsService;
    }

    @Override
    public Optional<HouseholdMember> handle(CreateHouseholdMemberCommand command) {
        var profile = profilesRepository.findById(command.profileId());
        if (profile.isEmpty()) return Optional.empty();
        var householdMember = new HouseholdMember(command, profile.get());
        householdMemberRepository.save(householdMember);
        return Optional.of(householdMember);
    }

    @Override
    public Optional<HouseholdMember> handle(UpdateHouseholdMemberCommand command) {
        var contact = householdMemberRepository.findById(command.id());
        if (contact.isEmpty()) return Optional.empty();
        contact.get().setName(command.name());
        contact.get().setEmail(command.email());
        contact.get().setPhone(command.phone());
        contact.get().setEmergencyContact(command.emergencyContact());
        contact.get().setGasAlerts(command.gasAlerts());
        householdMemberRepository.save(contact.get());
        return contact;
    }

    @Override
    public Optional<Long> handle(DeleteHouseholdMemberCommand command) {
        var contact = householdMemberRepository.findById(command.id());
        if (contact.isEmpty()) return Optional.empty();
        Long id = contact.get().getId();
        householdMemberRepository.delete(contact.get());
        return Optional.of(id);
    }

    @Override
    public void handle(SendSMSToEmergencyContactCommand command) {
        var contacts = householdMemberRepository.findAllByProfileId(command.profileId());
        String message = "";
        for (HouseholdMember contact : contacts) {
            if (contact.isGasAlerts()) {
                if ("WARNING".equals(command.status())) {
                    message = "⚠️ Alerta de gas detectada en el hogar.\nVerifica el ambiente y ventila la zona.";
                } else if ("ALERT".equals(command.status())) {
                    message = "\uD83D\uDEA8 Nivel de gas elevado en la siguiente ubicación: " + command.location() + ".\nAbre puertas/ventanas y cierra la válvula.";
                }
            }
            if (contact.isEmergencyContact() && "ALERT".equals(command.status())) {
                message = "⚠️ Alerta crítica: se ha detectado una posible fuga de gas en la vivienda de " + command.name() + ".\nComunícate o acude si es necesario.";
            }

            smsService.sendSMS(contact.getPhone(), message);
        }
    }
}
