package com.jcaa.udec.collections.adapter.persistence.memory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.EstudianteNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.EstudianteYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstudiantesAdapterTest {
    private static final AtomicInteger SECUENCIA = new AtomicInteger(2000);
    private final GuardarEstudianteAdapter guardar = new GuardarEstudianteAdapter();
    private final ObtenerEstudiantesAdapter obtener = new ObtenerEstudiantesAdapter();
    private final ActualizarEstudianteAdapter actualizar = new ActualizarEstudianteAdapter();
    private final EliminarEstudianteAdapter eliminar = new EliminarEstudianteAdapter();

    @BeforeEach
    void limpiar() {
        EstudiantesMemoria.obtenerEstudiantes().clear();
    }

    @Test
    void deberiaGuardarBuscarYListar() {
        Estudiante estudiante = crearEstudiante();
        guardar.guardar(estudiante);

        assertThat(obtener.buscarPorId(estudiante.getId())).isSameAs(estudiante);
        assertThat(obtener.obtenerTodos()).containsExactly(estudiante);
    }

    @Test
    void deberiaRechazarDuplicado() {
        Estudiante estudiante = crearEstudiante();
        guardar.guardar(estudiante);

        assertThatThrownBy(() -> guardar.guardar(estudiante))
                .isInstanceOf(EstudianteYaExisteException.class)
                .hasMessage("El estudiante ya existe.");
    }

    @Test
    void deberiaActualizar() {
        Estudiante estudiante = crearEstudiante();
        guardar.guardar(estudiante);
        Estudiante actualizado = new Estudiante(
                estudiante.getId(), "Nuevo Nombre", "nuevo_nombre@example.com", "87654321", "C1");

        actualizar.actualizar(actualizado);

        assertThat(obtener.buscarPorId(estudiante.getId()).getCategoria()).isEqualTo("C1");
    }

    @Test
    void deberiaEliminar() {
        Estudiante estudiante = crearEstudiante();
        guardar.guardar(estudiante);

        eliminar.eliminarPorId(estudiante.getId());

        assertThat(obtener.obtenerTodos()).isEmpty();
        assertThatThrownBy(() -> obtener.buscarPorId(estudiante.getId()))
                .isInstanceOf(EstudianteNoExisteException.class);
    }

    @Test
    void deberiaReportarInexistenteAlActualizarYEliminar() {
        assertThatThrownBy(() -> actualizar.actualizar(crearEstudiante()))
                .isInstanceOf(EstudianteNoExisteException.class);
        assertThatThrownBy(() -> eliminar.eliminarPorId("999999"))
                .isInstanceOf(EstudianteNoExisteException.class);
    }

    private static Estudiante crearEstudiante() {
        String id = String.valueOf(SECUENCIA.incrementAndGet());
        return new Estudiante(id, "Carlos Rangel", "carlos_rangel@example.com", "12345678", "B1");
    }
}
