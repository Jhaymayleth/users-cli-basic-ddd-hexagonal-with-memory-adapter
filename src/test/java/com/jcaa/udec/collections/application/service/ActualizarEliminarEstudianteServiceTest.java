package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEstudianteComando;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.ActualizarEstudiantePort;
import com.jcaa.udec.collections.domain.port.out.EliminarEstudiantePort;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ActualizarEliminarEstudianteServiceTest {
    @Test
    void deberiaActualizarEstudiante() {
        ActualizarPortStub actualizarPort = new ActualizarPortStub();
        ActualizarEstudianteService service = new ActualizarEstudianteService(actualizarPort);

        service.actualizar(
                new ActualizarEstudianteComando("101", "Carlos Rangel", "carlos_rangel@example.com", "12345678", "C1"));

        assertThat(actualizarPort.getActualizados())
                .singleElement()
                .extracting(Estudiante::getId, Estudiante::getCategoria)
                .containsExactly("101", "C1");
    }

    @Test
    void deberiaEliminarPorId() {
        EliminarPortStub eliminarPort = new EliminarPortStub();
        EliminarEstudianteService service = new EliminarEstudianteService(eliminarPort);

        service.eliminarPorId("101");

        assertThat(eliminarPort.getEliminados()).containsExactly("101");
    }

    private static final class ActualizarPortStub implements ActualizarEstudiantePort {
        private final List<Estudiante> actualizados = new ArrayList<>();

        @Override
        public void actualizar(Estudiante estudiante) {
            actualizados.add(estudiante);
        }

        private List<Estudiante> getActualizados() {
            return List.copyOf(actualizados);
        }
    }

    private static final class EliminarPortStub implements EliminarEstudiantePort {
        private final List<String> eliminados = new ArrayList<>();

        @Override
        public void eliminarPorId(String id) {
            eliminados.add(id);
        }

        private List<String> getEliminados() {
            return List.copyOf(eliminados);
        }
    }
}
