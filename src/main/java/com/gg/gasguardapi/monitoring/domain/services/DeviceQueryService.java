package com.gg.gasguardapi.monitoring.domain.services;

import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.domain.model.queries.GetAllDevicesByProfileId;

import java.util.List;

public interface DeviceQueryService {
    List<Device> handle(GetAllDevicesByProfileId query);
}
