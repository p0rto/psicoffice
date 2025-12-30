package com.p0rto.psicoffice.common.exception;

public class UnauthorizedException extends RuntimeException {
    private static final String DEFAULT_EXCEPTION = "Credenciais incorretas.";

    public UnauthorizedException() {
        super(DEFAULT_EXCEPTION);
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
