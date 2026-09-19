package com.jcaa.udec.collections.entrypoint.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEstudianteComando;
import com.jcaa.udec.collections.application.service.dto.command.CrearEstudianteComando;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerEstudianteConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.EliminarEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.RegistrarEstudianteUseCase;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarEstudiantePeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarEstudiantePeticion;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class EstudianteControladorImplTest {
    private static final String ID = "101";
    private static final String NOMBRE = "Carlos Rangel";
    private static final String EMAIL = "carlos_rangel@example.com";
    private static final String DOCUMENTO = "12345678";
    private static final String CATEGORIA = "B1";

    @Test
    void deberiaOrquestarCrudl() {
        RegistrarStub registrar = new RegistrarStub();
        ObtenerStub obtener = new ObtenerStub();
        ActualizarStub actualizar = new ActualizarStub();
        EliminarStub eliminar = new EliminarStub();
        EstudianteControlador controlador =
                new EstudianteControladorImpl(registrar, obtener, actualizar, eliminar);

        controlador.registrar(new RegistrarEstudiantePeticion(ID, NOMBRE, EMAIL, DOCUMENTO, CATEGORIA));
        controlador.actualizar(new ActualizarEstudiantePeticion(ID, NOMBRE, EMAIL, DOCUMENTO, "C1"));
        controlador.eliminar(ID);

        assertThat(controlador.obtenerPorId(ID).estudiantes()).hasSize(1);
        assertThat(controlador.obtenerTodos().estudiantes()).hasSize(1);
        assertThat(registrar.creados).hasSize(1);
        assertThat(actualizar.actualizados).hasSize(1);
        assertThat(eliminar.eliminados).containsExactly(ID);
    }

    private Estudiante crearEstudiante() {
        return new Estudiante(ID, NOMBRE, EMAIL, DOCUMENTO, CATEGORIA);
    }

    private final class RegistrarStub implements RegistrarEstudianteUseCase {
        private final List<CrearEstudianteComando> creados = new ArrayList<>();

        @Override
        public void guardar(CrearEstudianteComando comando) {
            creados.add(comando);
        }
    }

    private final class ObtenerStub implements ObtenerEstudianteUseCase {
        @Override
        public List<Estudiante> obtenerTodos() {
            return List.of(crearEstudiante());
        }

        @Override
        public Estudiante obtenerPorId(ObtenerEstudianteConsulta consulta) {
            return crearEstudiante();
        }
    }

    private final class ActualizarStub implements ActualizarEstudianteUseCase {
        private final List<ActualizarEstudianteComando> actualizados = new ArrayList<>();

        @Override
        public void actualizar(ActualizarEstudianteComando comando) {
            actualizados.add(comando);
        }
    }

    private final class EliminarStub implements EliminarEstudianteUseCase {
        private final List<String> eliminados = new ArrayList<>();

        @Override
        public void eliminarPorId(String id) {
            eliminados.add(id);
        }
    }
}
