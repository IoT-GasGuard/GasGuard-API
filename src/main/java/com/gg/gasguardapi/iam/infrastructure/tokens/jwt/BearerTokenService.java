package com.gg.gasguardapi.iam.infrastructure.tokens.jwt;

import com.gg.gasguardapi.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {
    String getBearerTokenFrom(HttpServletRequest token);
    String generateToken(Authentication authentication);
}
