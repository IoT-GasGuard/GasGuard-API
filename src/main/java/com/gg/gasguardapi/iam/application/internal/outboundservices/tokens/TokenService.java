package com.gg.gasguardapi.iam.application.internal.outboundservices.tokens;

public interface TokenService {
    String generateToken(String username);
    String getUsernameFromToken(String token);
    boolean validateToken(String token);

    String generateResetPasswordToken(String username);
    boolean validateResetPasswordToken(String token);
    boolean isPasswordResetToken(String token);
}
