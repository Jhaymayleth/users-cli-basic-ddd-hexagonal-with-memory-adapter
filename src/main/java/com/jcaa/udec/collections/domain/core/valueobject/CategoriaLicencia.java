package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EstudianteInvalidoException;
import java.util.Objects;
import java.util.Set;

public record CategoriaLicencia(String valor) {
    private static final Set<String> CATEGORIAS_VALIDAS = Set.of("A1", "A2", "B1", "C1");

    public CategoriaLicencia {
        if (Objects.isNull(valor) || valor.isBlank() || !CATEGORIAS_VALIDAS.contains(valor.trim().toUpperCase())) {
            throw new EstudianteInvalidoException();
        }
    }
}
