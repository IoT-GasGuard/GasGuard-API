package com.gg.gasguardapi.profiles.domain.model.entities;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.profiles.domain.model.commands.CreateHouseholdMemberCommand;
import com.gg.gasguardapi.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;

@Entity
public class HouseholdMember extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean emergencyContact;
    private boolean gasAlerts;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profiles profile;

    public HouseholdMember() {
    }

    public HouseholdMember(CreateHouseholdMemberCommand command, Profiles profile) {
        this.name= command.name();
        this.email = command.email();
        this.phone = command.phone();
        this.emergencyContact=command.emergencyContact();
        this.gasAlerts=command.gasAlerts();
        this.profile=profile;
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isEmergencyContact() {
        return emergencyContact;
    }

    public boolean isGasAlerts() {
        return gasAlerts;
    }

    public Profiles getProfile() {
        return profile;
    }
}
