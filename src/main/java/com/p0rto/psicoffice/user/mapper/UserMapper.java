package com.p0rto.psicoffice.user.mapper;

import org.springframework.stereotype.Component;

import com.p0rto.psicoffice.user.dto.RegisterRequestDTO;
import com.p0rto.psicoffice.user.dto.UserResponseDTO;
import com.p0rto.psicoffice.user.entity.User;

@Component
public class UserMapper {
    public User toEntity(RegisterRequestDTO dto) {
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

    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getBirthDate(), user.getCpf(), user.getEmail(), user.getCrp());
    }
}
