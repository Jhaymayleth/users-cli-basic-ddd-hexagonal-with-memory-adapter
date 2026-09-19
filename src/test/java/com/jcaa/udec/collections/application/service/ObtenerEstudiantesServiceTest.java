package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerEstudianteConsulta;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.ObtenerEstudiantesPort;
import java.util.List;
import org.junit.jupiter.api.Test;

class ObtenerEstudiantesServiceTest {
    @Test
    void deberiaObtenerTodosYPorId() {
        Estudiante estudiante = new Estudiante("101", "Carlos Rangel", "carlos_rangel@example.com", "12345678", "B1");
        ObtenerEstudiantesPortStub port = new ObtenerEstudiantesPortStub(estudiante);
        ObtenerEstudiantesService service = new ObtenerEstudiantesService(port);

        assertThat(service.obtenerTodos()).containsExactly(estudiante);
        assertThat(service.obtenerPorId(new ObtenerEstudianteConsulta("101"))).isSameAs(estudiante);
    }

    private static final class ObtenerEstudiantesPortStub implements ObtenerEstudiantesPort {
        private final Estudiante estudiante;

        private ObtenerEstudiantesPortStub(Estudiante estudiante) {
            this.estudiante = estudiante;
        }

        @Override
        public List<Estudiante> obtenerTodos() {
            return List.of(estudiante);
        }

        @Override
        public Estudiante buscarPorId(String id) {
            return estudiante;
        }
    }
}
