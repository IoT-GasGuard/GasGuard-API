package com.gg.gasguardapi.profiles.interfaces.rest;

import com.gg.gasguardapi.profiles.domain.model.queries.GetAllHouseholdMemberByProfileId;
import com.gg.gasguardapi.profiles.domain.services.HouseholdMemberCommandService;
import com.gg.gasguardapi.profiles.domain.services.HouseholdMemberQueryService;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.CreateHouseholdMemberResource;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.HouseholdMemberResource;
import com.gg.gasguardapi.profiles.interfaces.rest.transform.CreateHouseholdMemberCommandFromResourceAssembler;
import com.gg.gasguardapi.profiles.interfaces.rest.transform.HouseholdMemberResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
public class HouseholdMembersController {
    private final HouseholdMemberCommandService householdMemberCommandService;
    private final HouseholdMemberQueryService householdMemberQueryService;

    public HouseholdMembersController(
            HouseholdMemberCommandService householdMemberCommandService,
            HouseholdMemberQueryService householdMemberQueryService) {
        this.householdMemberCommandService = householdMemberCommandService;
        this.householdMemberQueryService = householdMemberQueryService;
    }

    @PostMapping
    public ResponseEntity<HouseholdMemberResource> createHouseholdMember(
            @RequestBody CreateHouseholdMemberResource householdMemberResource) {
        var command = CreateHouseholdMemberCommandFromResourceAssembler.toCommandFromResource(householdMemberResource);
        var contact = householdMemberCommandService.handle(command);
        if(contact.isEmpty())return ResponseEntity.badRequest().build();
        var resource = HouseholdMemberResourceFromEntityAssembler.toResourceFromEntity(contact.get());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<List<HouseholdMemberResource>> getHouseholdMembers(@PathVariable Long id){
        var contacts = householdMemberQueryService.handle(new GetAllHouseholdMemberByProfileId(id));
        var resources = contacts.stream()
                .map(HouseholdMemberResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }
}
