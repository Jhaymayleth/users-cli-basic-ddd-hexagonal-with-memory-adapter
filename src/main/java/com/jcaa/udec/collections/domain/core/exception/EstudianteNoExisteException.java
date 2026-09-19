package com.jcaa.udec.collections.domain.core.exception;

public class EstudianteNoExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "El estudiante no existe.";

    public EstudianteNoExisteException() {
        super(MENSAJE_ERROR);
    }
}
