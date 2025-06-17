package com.gg.gasguardapi.iam.infrastructure.authorization.sfs.pipeline;

import com.gg.gasguardapi.iam.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import com.gg.gasguardapi.iam.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);
    private final BearerTokenService tokenService;


    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * This method is responsible for filtering requests and setting the user authentication.
     * @param request The request object.
     * @param response The response object.
     * @param filterChain The filter chain object.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = tokenService.getBearerTokenFrom(request);
            LOGGER.info("Token: {}", token);

            if(token != null) {
                if(tokenService.isPasswordResetToken(token)){
                    LOGGER.info("Reset Password Token detected");

                    if(request.getRequestURI().startsWith("/api/v1/auth/reset-password")){
                        if(tokenService.validateResetPasswordToken(token)){
                            LOGGER.info("Reset Password Token is valid");
                            request.setAttribute("resetPasswordUsername", tokenService.getUsernameFromToken(token));

                        } else {
                            LOGGER.warn("Token de recuperación inválido o expirado");
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token de recuperación inválido");
                            return;
                        }
                    } else {
                        LOGGER.warn("Intento de usar un token de recuperación en una ruta no autorizada");
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado");
                        return;
                    }

                } else if (tokenService.validateToken(token) && !request.getRequestURI().startsWith("/api/v1/auth/reset-password")) {
                    String username = tokenService.getUsernameFromToken(token);
                    var userDetails = userDetailsService.loadUserByUsername(username);
                    SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
                } else {
                    LOGGER.info("Token is not valid");
                }
            }

        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
