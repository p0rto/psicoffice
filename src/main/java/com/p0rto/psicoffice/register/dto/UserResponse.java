package com.p0rto.psicoffice.register.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponse(UUID id, String name, LocalDate birthDate, String cpf, String email, String crp) {

}
