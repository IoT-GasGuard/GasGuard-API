package com.gg.gasguardapi.iam.interfaces.rest;

import com.gg.gasguardapi.iam.domain.model.commands.SignInUserCommand;
import com.gg.gasguardapi.iam.domain.model.commands.SignUpUserCommand;
import com.gg.gasguardapi.iam.domain.services.UserCommandService;
import com.gg.gasguardapi.iam.interfaces.rest.resources.AuthenticatedUserResource;
import com.gg.gasguardapi.iam.interfaces.rest.resources.SignInResource;
import com.gg.gasguardapi.iam.interfaces.rest.resources.SignUpResource;
import com.gg.gasguardapi.iam.interfaces.rest.resources.UserResource;
import com.gg.gasguardapi.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import com.gg.gasguardapi.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/api/v1/auth",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Operations related to users authentication")
public class AuthController {
    private final UserCommandService userCommandService;

    public AuthController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @Operation(summary = "Create User")
    @PostMapping(value = "/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource resource){
        var user = userCommandService.handle(new SignUpUserCommand(resource.email(), resource.password()));
        if (user.isEmpty())return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Sign In")
    @PostMapping(value = "/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody SignInResource resource){
        var user = userCommandService.handle(new SignInUserCommand(resource.email(), resource.password()));
        if (user.isEmpty())return ResponseEntity.badRequest().build();
        var userResource = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(user.get().getLeft(),user.get().getRight());
        return ResponseEntity.ok(userResource);
    }
}
