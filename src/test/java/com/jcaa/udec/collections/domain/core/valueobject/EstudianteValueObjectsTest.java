package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.EstudianteInvalidoException;
import org.junit.jupiter.api.Test;

class EstudianteValueObjectsTest {
    @Test
    void deberiaRechazarIdsInvalidos() {
        assertThatThrownBy(() -> new EstudianteId(null)).isInstanceOf(EstudianteInvalidoException.class);
        assertThatThrownBy(() -> new EstudianteId("")).isInstanceOf(EstudianteInvalidoException.class);
        assertThatThrownBy(() -> new EstudianteId("abc")).isInstanceOf(EstudianteInvalidoException.class);
    }

    @Test
    void deberiaRechazarNombresCortos() {
        assertThatThrownBy(() -> new NombreCompleto("An")).isInstanceOf(EstudianteInvalidoException.class);
        assertThatThrownBy(() -> new NombreCompleto("  ")).isInstanceOf(EstudianteInvalidoException.class);
    }

    @Test
    void deberiaRechazarDocumentosInvalidos() {
        assertThatThrownBy(() -> new DocumentoIdentidad("12345")).isInstanceOf(EstudianteInvalidoException.class);
        assertThatThrownBy(() -> new DocumentoIdentidad("1234567890123"))
                .isInstanceOf(EstudianteInvalidoException.class);
        assertThatThrownBy(() -> new DocumentoIdentidad("12ab5678"))
                .isInstanceOf(EstudianteInvalidoException.class);
    }

    @Test
    void deberiaRechazarCategoriasInvalidas() {
        assertThatThrownBy(() -> new CategoriaLicencia("B2")).isInstanceOf(EstudianteInvalidoException.class);
        assertThatThrownBy(() -> new CategoriaLicencia(null)).isInstanceOf(EstudianteInvalidoException.class);
    }
}
