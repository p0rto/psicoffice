package com.p0rto.psicoffice.register.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank
    @Size(max = 150)
    String name,

    @Email
    @NotBlank
    @Size(max = 150)
    String email,

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String cpf,

    @NotBlank
    @Size(min = 8, max = 255)
    String password,

    @Size(max = 20)
    String crp,

    @Size(max = 20)
    String phone,

    @NotNull
    @Past
    LocalDate birthDate
) {

}
