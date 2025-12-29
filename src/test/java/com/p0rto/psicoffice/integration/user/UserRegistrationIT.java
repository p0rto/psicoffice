package com.p0rto.psicoffice.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p0rto.psicoffice.user.dto.RegisterRequest;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class UserRegistrationIT {
    private static final String USER_PATH = "/api/users";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterUserSuccesfully() throws Exception {
        RegisterRequest requestDto = createUserRequest("00000000001", "email@teste.com");

        mockMvc.perform(MockMvcRequestBuilders.post(USER_PATH + "/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestDto)))
          .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnBadRequestWhenUserEmailAlreadyExists() throws Exception {
        RegisterRequest requestDto = createUserRequest("00000000002", "email@teste.com");

        // register
        mockMvc.perform(MockMvcRequestBuilders.post(USER_PATH + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isCreated());

        RegisterRequest requestDtoDuplicatedEmail = createUserRequest("00000000001", "email@teste.com");

        // duplicated email
        mockMvc.perform(MockMvcRequestBuilders.post(USER_PATH + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDtoDuplicatedEmail)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value("Usuário já existe."));
    }

    @Test
    void shouldReturnBadRequestWhenUserCpfAlreadyExists() throws Exception {
        RegisterRequest requestDto = createUserRequest("00000000001", "email@teste.com");

        // register
        mockMvc.perform(MockMvcRequestBuilders.post(USER_PATH + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isCreated());

        RegisterRequest requestDtoDuplicatedCpf = createUserRequest("00000000001", "newemail@teste.com");

        // duplicated cpf
        mockMvc.perform(MockMvcRequestBuilders.post(USER_PATH + "/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDtoDuplicatedCpf)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message").value("Usuário já existe."));
    }

    private RegisterRequest createUserRequest(String cpf, String email) {
        return new RegisterRequest(
            "Teste Name",
            email,
            cpf,
            "password123",
            "012345",
            "55999999999",
            LocalDate.of(1991, 1, 1)
        );
    }
}
