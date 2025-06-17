package com.gg.gasguardapi.iam.domain.model.aggregates;

import com.gg.gasguardapi.profiles.domain.model.aggregates.Profiles;
import com.gg.gasguardapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;

@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id",unique = true, nullable = false)
    private Profiles profiles;

    public User(){}

    public User(String email, String password, Profiles profiles) {
        this.email = email;
        this.password = password;
        this.profiles = profiles;
    }

    public Profiles getProfiles() {
        return profiles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
