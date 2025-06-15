package com.gg.gasguardapi.monitoring.domain.services;

import com.gg.gasguardapi.monitoring.domain.model.aggregates.Device;
import com.gg.gasguardapi.monitoring.domain.model.queries.GetAllDevicesByProfileId;
import com.gg.gasguardapi.monitoring.domain.model.queries.GetByDeviceIdQuery;

import java.util.List;
import java.util.Optional;

public interface DeviceQueryService {
    List<Device> handle(GetAllDevicesByProfileId query);
    Optional<Device> handle(GetByDeviceIdQuery query);
}
