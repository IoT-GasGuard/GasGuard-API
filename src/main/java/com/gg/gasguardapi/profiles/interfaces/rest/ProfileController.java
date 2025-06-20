package com.gg.gasguardapi.profiles.interfaces.rest;

import com.gg.gasguardapi.profiles.domain.model.queries.GetProfileByIdQuery;
import com.gg.gasguardapi.profiles.domain.services.ProfilesQueryService;
import com.gg.gasguardapi.profiles.interfaces.rest.resources.ProfileResource;
import com.gg.gasguardapi.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api/v1/profiles",produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {
    private final ProfilesQueryService profilesQueryService;

    public ProfileController(ProfilesQueryService profilesQueryService) {
        this.profilesQueryService = profilesQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResource> getProfile(@PathVariable Long id) {
        var profile = profilesQueryService.handle(new GetProfileByIdQuery(id));
        if (profile.isEmpty())return ResponseEntity.notFound().build();
        var resource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(resource);
    }
}
