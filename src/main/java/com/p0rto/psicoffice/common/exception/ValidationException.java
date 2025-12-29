package com.p0rto.psicoffice.common.exception;

public class ValidationException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Erro de validação.";

    public ValidationException() {
        super(DEFAULT_MESSAGE);
    }
    
    public ValidationException(String message) {
        super(message);
    }
}
