package com.gg.gasguardapi.iam.domain.model.aggregates;

import com.gg.gasguardapi.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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
