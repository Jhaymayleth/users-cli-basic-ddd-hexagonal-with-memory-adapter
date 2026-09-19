package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EstudianteInvalidoException;
import java.util.Objects;

public record DocumentoIdentidad(String valor) {
    private static final int LONGITUD_MINIMA = 6;
    private static final int LONGITUD_MAXIMA = 12;

    public DocumentoIdentidad {
        if (Objects.isNull(valor)
                || valor.isBlank()
                || valor.trim().length() < LONGITUD_MINIMA
                || valor.trim().length() > LONGITUD_MAXIMA
                || !esNumerico(valor.trim())) {
            throw new EstudianteInvalidoException();
        }
    }

    private static boolean esNumerico(String valor) {
        for (int i = 0; i < valor.length(); i++) {
            if (!Character.isDigit(valor.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
