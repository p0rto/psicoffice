package com.p0rto.psicoffice.integration.util;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.p0rto.psicoffice.user.dto.RegisterRequest;
import com.p0rto.psicoffice.user.service.UserService;

@Component
public class IntegrationTestSupport {
    @Autowired
    UserService userService;

    public RegisterRequest createDefaultUser() {
        RegisterRequest dto = new RegisterRequest(
            "Teste Name",
            "teste@email.com",
            "99999999900",
            "password123",
            "012345",
            "55999999999",
            LocalDate.of(1991, 1, 1)
        );

        this.userService.create(dto);

        return dto;
    }
}
