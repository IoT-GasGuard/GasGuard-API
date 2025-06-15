package com.gg.gasguardapi.reports.domain.model.valueObjects;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Protocols {
    WINDOWS_OPENED("Windows opened"),
    POWER_SUPPLY_SHUT_OFF("Power supply shut off"),
    ALERT_SENT_TO_EMERGENCY_CONTACTS("Alert sent to emergency contacts");

    private final String label;

    Protocols(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
