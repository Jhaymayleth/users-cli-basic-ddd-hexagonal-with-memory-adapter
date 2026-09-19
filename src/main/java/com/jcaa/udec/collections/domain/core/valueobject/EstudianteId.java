package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EstudianteInvalidoException;
import java.util.Objects;

public record EstudianteId(String valor) {
    public EstudianteId {
        if (Objects.isNull(valor) || valor.isBlank() || !esNumeroEntero(valor)) {
            throw new EstudianteInvalidoException();
        }
    }

    private static boolean esNumeroEntero(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
