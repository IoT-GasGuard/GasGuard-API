package com.gg.gasguardapi.iam.infrastructure.hashing.bcrypt;

import com.gg.gasguardapi.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
