package com.gg.gasguardapi.profiles.interfaces.rest;

import com.gg.gasguardapi.profiles.domain.model.queries.GetProfileByIdQuery;
import com.gg.gasguardapi.profiles.domain.services.ProfilesCommandService;
import com.gg.gasguardapi.profiles.domain.services.ProfilesQueryService;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.ProfileResource;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.UpdateProfileResource;
import com.gg.gasguardapi.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.gg.gasguardapi.profiles.interfaces.rest.transform.UpdateProfileCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api/v1/profiles",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Operations related to profiles")
public class ProfileController {
    private final ProfilesQueryService profilesQueryService;
    private final ProfilesCommandService profilesCommandService;

    public ProfileController(
            ProfilesQueryService profilesQueryService,
            ProfilesCommandService profilesCommandService) {
        this.profilesQueryService = profilesQueryService;
        this.profilesCommandService = profilesCommandService;
    }

    @Operation(summary = "Get Profile By Id")
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResource> getProfile(@PathVariable Long id) {
        var profile = profilesQueryService.handle(new GetProfileByIdQuery(id));
        if (profile.isEmpty())return ResponseEntity.notFound().build();
        var resource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(resource);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update Profile")
    public ResponseEntity<ProfileResource> updateProfile(
            @PathVariable Long id,
            @RequestBody UpdateProfileResource updateProfileResource){
        var command = UpdateProfileCommandFromResourceAssembler
                .toCommandFromResource(updateProfileResource,id);
        var profile = profilesCommandService.handle(command);
        if(profile.isEmpty())return ResponseEntity.notFound().build();
        var resource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(resource);
    }
}
