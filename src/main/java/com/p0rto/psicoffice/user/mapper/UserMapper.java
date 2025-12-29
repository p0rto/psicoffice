package com.p0rto.psicoffice.user.mapper;

import org.springframework.stereotype.Component;

import com.p0rto.psicoffice.user.dto.RegisterRequest;
import com.p0rto.psicoffice.user.dto.UserResponse;
import com.p0rto.psicoffice.user.entity.User;

@Component
public class UserMapper {
    public User toEntity(RegisterRequest dto) {
        User user = new User();

        user.setName(dto.name());
        user.setCpf(dto.cpf());
        user.setPhone(dto.phone());
        user.setPassword(dto.password());
        user.setEmail(dto.email());
        user.setCrp(dto.crp());
        user.setBirthDate(dto.birthDate());

        return user;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getBirthDate(), user.getCpf(), user.getEmail(), user.getCrp());
    }
}
