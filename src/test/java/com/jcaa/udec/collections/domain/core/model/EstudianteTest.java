package com.jcaa.udec.collections.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.EstudianteInvalidoException;
import org.junit.jupiter.api.Test;

class EstudianteTest {
    private static final String ID = "101";
    private static final String NOMBRE = "Carlos Rangel";
    private static final String EMAIL = "carlos_rangel@example.com";
    private static final String DOCUMENTO = "12345678";
    private static final String CATEGORIA = "B1";

    @Test
    void deberiaCrearEstudianteValido() {
        Estudiante estudiante = Estudiante.builder()
                .id(ID)
                .nombre(NOMBRE)
                .email(EMAIL)
                .documento(DOCUMENTO)
                .categoria(CATEGORIA)
                .build();

        assertThat(estudiante.getId()).isEqualTo(ID);
        assertThat(estudiante.getNombre()).isEqualTo(NOMBRE);
        assertThat(estudiante.getEmail()).isEqualTo(EMAIL);
        assertThat(estudiante.getDocumento()).isEqualTo(DOCUMENTO);
        assertThat(estudiante.getCategoria()).isEqualTo(CATEGORIA);
    }

    @Test
    void deberiaRechazarDocumentoInvalido() {
        assertThatThrownBy(() -> Estudiante.builder()
                        .id(ID)
                        .nombre(NOMBRE)
                        .email(EMAIL)
                        .documento("abc")
                        .categoria(CATEGORIA)
                        .build())
                .isInstanceOf(EstudianteInvalidoException.class);
    }

    @Test
    void deberiaRechazarCategoriaInvalida() {
        assertThatThrownBy(() -> Estudiante.builder()
                        .id(ID)
                        .nombre(NOMBRE)
                        .email(EMAIL)
                        .documento(DOCUMENTO)
                        .categoria("Z9")
                        .build())
                .isInstanceOf(EstudianteInvalidoException.class);
    }
}
