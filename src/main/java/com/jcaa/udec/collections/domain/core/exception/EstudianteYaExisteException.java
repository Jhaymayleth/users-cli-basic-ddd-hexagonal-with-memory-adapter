package com.jcaa.udec.collections.domain.core.exception;

public class EstudianteYaExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "El estudiante ya existe.";

    public EstudianteYaExisteException() {
        super(MENSAJE_ERROR);
    }
}
