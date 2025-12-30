package com.p0rto.psicoffice.register.service;

import org.springframework.stereotype.Service;

import com.p0rto.psicoffice.common.exception.ValidationException;
import com.p0rto.psicoffice.register.dto.RegisterRequest;
import com.p0rto.psicoffice.register.entity.User;
import com.p0rto.psicoffice.register.mapper.UserMapper;
import com.p0rto.psicoffice.register.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public void create(RegisterRequest dto) {
        if (repository.existsByCpfOrEmail(dto.cpf(), dto.email())) {
            throw new ValidationException("Usuário já existe.");
        }

        User user = mapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        
        repository.save(user);
    }
}
