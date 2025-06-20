package com.gg.gasguardapi.monitoring.interfaces.rest;

import com.gg.gasguardapi.monitoring.domain.model.commands.DeleteDeviceCommand;
import com.gg.gasguardapi.monitoring.domain.model.queries.GetAllDevicesByProfileId;
import com.gg.gasguardapi.monitoring.domain.services.DeviceCommandService;
import com.gg.gasguardapi.monitoring.domain.services.DeviceQueryService;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.CreateDeviceResource;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.DeletedDevice;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.DeviceResource;
import com.gg.gasguardapi.monitoring.interfaces.rest.resources.UpdateDeviceResource;
import com.gg.gasguardapi.monitoring.interfaces.rest.transform.CreateDeviceCommandFromResourceAssembler;
import com.gg.gasguardapi.monitoring.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
import com.gg.gasguardapi.monitoring.interfaces.rest.transform.UpdateDeviceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/devices",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Devices", description = "Operations related to devices")
public class DeviceController {
    private final DeviceCommandService deviceCommandService;
    private final DeviceQueryService deviceQueryService;

    public DeviceController(
            DeviceCommandService deviceCommandService,
            DeviceQueryService deviceQueryService) {
        this.deviceCommandService = deviceCommandService;
        this.deviceQueryService = deviceQueryService;
    }

    @Operation(summary = "Create Device")
    @PostMapping
    public ResponseEntity<DeviceResource> addDevice(@RequestBody CreateDeviceResource createDeviceResource) {
        var command = CreateDeviceCommandFromResourceAssembler.toCommandFromResource(createDeviceResource);
        var device = deviceCommandService.handle(command);
        if(device.isEmpty())return ResponseEntity.badRequest().build();
        var resource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Devices By Profile Id")
    @GetMapping("/profile/{id}")
    public ResponseEntity<List<DeviceResource>> getAllDevicesByProfileId(@PathVariable Long id) {
        var devices = deviceQueryService.handle(new GetAllDevicesByProfileId(id));
        var resources = devices.stream()
                .map(DeviceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Update Device")
    @PatchMapping("/{id}")
    public ResponseEntity<DeviceResource> updateDevice(
            @PathVariable Long id,
            @RequestBody UpdateDeviceResource updateDeviceResource){
        var command = UpdateDeviceCommandFromResourceAssembler.toCommandFromResource(id, updateDeviceResource);
        var device = deviceCommandService.handle(command);
        if(device.isEmpty())return ResponseEntity.badRequest().build();
        var resource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Delete Device")
    @DeleteMapping("/{id}")
    public ResponseEntity<DeletedDevice> deleteDevice(@PathVariable Long id) {
        var deviceId = deviceCommandService.handle(new DeleteDeviceCommand(id));
        if (deviceId.isEmpty())return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(new DeletedDevice(deviceId.get()));
    }

}
