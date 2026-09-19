package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EstudianteInvalidoException;
import java.util.Objects;

public record NombreCompleto(String valor) {
    private static final int LONGITUD_MINIMA = 3;

    public NombreCompleto {
        if (Objects.isNull(valor) || valor.isBlank() || valor.trim().length() < LONGITUD_MINIMA) {
            throw new EstudianteInvalidoException();
        }
    }
}
