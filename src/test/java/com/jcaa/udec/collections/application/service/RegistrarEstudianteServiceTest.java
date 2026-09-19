package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.CrearEstudianteComando;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.GuardarEstudiantePort;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class RegistrarEstudianteServiceTest {
    @Test
    void deberiaMapearYGuardarEstudiante() {
        GuardarEstudiantePortStub port = new GuardarEstudiantePortStub();
        RegistrarEstudianteService service = new RegistrarEstudianteService(port);
        CrearEstudianteComando comando =
                new CrearEstudianteComando("101", "Carlos Rangel", "carlos_rangel@example.com", "12345678", "B1");

        service.guardar(comando);

        assertThat(port.getGuardados())
                .singleElement()
                .extracting(
                        Estudiante::getId,
                        Estudiante::getNombre,
                        Estudiante::getEmail,
                        Estudiante::getDocumento,
                        Estudiante::getCategoria)
                .containsExactly("101", "Carlos Rangel", "carlos_rangel@example.com", "12345678", "B1");
    }

    private static final class GuardarEstudiantePortStub implements GuardarEstudiantePort {
        private final List<Estudiante> guardados = new ArrayList<>();

        @Override
        public void guardar(Estudiante estudiante) {
            guardados.add(estudiante);
        }

        private List<Estudiante> getGuardados() {
            return List.copyOf(guardados);
        }
    }
}
