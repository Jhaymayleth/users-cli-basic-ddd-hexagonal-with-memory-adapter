package com.jcaa.udec.collections.domain.core.exception;

public class EstudianteInvalidoException extends RuntimeException {
    private static final String MENSAJE_ERROR = "Los datos del estudiante son invalidos.";

    public EstudianteInvalidoException() {
        super(MENSAJE_ERROR);
    }
}
