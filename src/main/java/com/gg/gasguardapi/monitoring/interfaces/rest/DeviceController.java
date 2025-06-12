package com.gg.gasguardapi.monitoring.interfaces.rest;

import com.gg.gasguardapi.monitoring.domain.model.commands.CreateDeviceCommand;
import com.gg.gasguardapi.monitoring.domain.model.queries.GetAllDevicesByProfileId;
import com.gg.gasguardapi.monitoring.domain.services.DeviceCommandService;
import com.gg.gasguardapi.monitoring.domain.services.DeviceQueryService;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.CreateDeviceResource;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.DeviceResource;
import com.gg.gasguardapi.monitoring.interfaces.rest.transform.CreateDeviceCommandFromResourceAssembler;
import com.gg.gasguardapi.monitoring.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/devices",produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceController {
    private final DeviceCommandService deviceCommandService;
    private final DeviceQueryService deviceQueryService;

    public DeviceController(
            DeviceCommandService deviceCommandService,
            DeviceQueryService deviceQueryService) {
        this.deviceCommandService = deviceCommandService;
        this.deviceQueryService = deviceQueryService;
    }

    @PostMapping
    public ResponseEntity<DeviceResource> addDevice(@RequestBody CreateDeviceResource createDeviceResource) {
        var command = CreateDeviceCommandFromResourceAssembler.toCommandFromResource(createDeviceResource);
        var device = deviceCommandService.handle(command);
        if(device.isEmpty())return ResponseEntity.badRequest().build();
        var resource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<List<DeviceResource>> getAllDevicesByProfileId(@PathVariable Long id) {
        var devices = deviceQueryService.handle(new GetAllDevicesByProfileId(id));
        var resources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

}
