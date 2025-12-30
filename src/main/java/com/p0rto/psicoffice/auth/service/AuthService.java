package com.p0rto.psicoffice.auth.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.p0rto.psicoffice.auth.dto.AuthRequest;
import com.p0rto.psicoffice.common.exception.UnauthorizedException;
import com.p0rto.psicoffice.register.entity.User;
import com.p0rto.psicoffice.register.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void authenticate(AuthRequest dto) {
        User user = userRepository.findByEmail(dto.email())
            .orElseThrow(UnauthorizedException::new);
        
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UnauthorizedException();
        }
    }
}
