package com.p0rto.psicoffice.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p0rto.psicoffice.user.dto.RegisterRequestDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationIT {
    private static final String USER_PATH = "/api/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterUserSuccesfully() throws Exception {
        RegisterRequestDTO requestDto = createUserRequest();

        mockMvc.perform(MockMvcRequestBuilders.post(USER_PATH + "/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))
          .andExpect(status().isCreated());
    }

    private RegisterRequestDTO createUserRequest() {
        return new RegisterRequestDTO(
            "Teste Name",
            "teste@email.com",
            "99999999900",
            "password123",
            "012345",
            "55999999999",
            LocalDate.of(1991, 1, 1)
        );
    }
}
