package com.gg.gasguardapi.profiles.domain.model.aggregates;

import com.gg.gasguardapi.profiles.domain.model.commands.CreateProfileCommand;
import com.gg.gasguardapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;

@Entity
public class Profiles extends AuditableAbstractAggregateRoot<Profiles> {
    private String name;
    private String email;
    private String phoneNumber;

    public Profiles() {
    }

    public Profiles(CreateProfileCommand command){
        this.name=command.name();
        this.email=command.email();
        this.phoneNumber=command.phoneNumber();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
