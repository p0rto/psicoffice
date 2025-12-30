package com.p0rto.psicoffice.integration.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p0rto.psicoffice.auth.dto.AuthRequest;
import com.p0rto.psicoffice.integration.util.IntegrationTestSupport;
import com.p0rto.psicoffice.register.dto.RegisterRequest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
public class AuthenticationIT {
    private static final String AUTH_PATH = "/api/auth";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private IntegrationTestSupport support;

    @Test
    void shouldAuthenticateSuccessfully() throws Exception {
        RegisterRequest userRegistered = support.createDefaultUser();
        AuthRequest authRequest = createAuthRequest(userRegistered.email(), userRegistered.password());

        mockMvc.perform(MockMvcRequestBuilders.post(AUTH_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(authRequest)))
          .andExpect(status().isOk());
    }

    @Test
    void shouldReturnUnauthorizedWhenUserDoesntExists() throws Exception {
        AuthRequest authRequest = createAuthRequest("userdoesntexists@gmail.com", "passworddoesntexists123");

        mockMvc.perform(MockMvcRequestBuilders.post(AUTH_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(authRequest)))
          .andExpect(status().isUnauthorized())
          .andExpect(jsonPath("$.message").value("Credenciais incorretas."));
    }

    @Test
    void shouldReturnUnauthorizedWhenPasswordIsIncorrect() throws Exception {
        RegisterRequest userRegistered = support.createDefaultUser();
        AuthRequest authRequest = createAuthRequest(userRegistered.email(), "incorrectpassword");

        mockMvc.perform(MockMvcRequestBuilders.post(AUTH_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(authRequest)))
          .andExpect(status().isUnauthorized())
          .andExpect(jsonPath("$.message").value("Credenciais incorretas."));
    }

    private AuthRequest createAuthRequest(String email, String password) {
        return new AuthRequest(email, password);
    }
}
