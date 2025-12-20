package com.p0rto.psicoffice.user.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(UUID id, String name, LocalDate birthDate, String cpf, String email, String crp) {

}
