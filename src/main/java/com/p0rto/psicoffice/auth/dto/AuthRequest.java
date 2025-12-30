package com.p0rto.psicoffice.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @NotBlank
    String email,

    @NotBlank
    String password
) {

}
