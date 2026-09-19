package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.EstudianteNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.ObtenerEstudiantesPort;

import java.util.List;
import java.util.Objects;

public class ObtenerEstudiantesAdapter implements ObtenerEstudiantesPort {
    private final List<Estudiante> estudiantes = EstudiantesMemoria.obtenerEstudiantes();

    @Override
    public List<Estudiante> obtenerTodos() {
        return List.copyOf(estudiantes);
    }

    @Override
    public Estudiante buscarPorId(String id) {
        for (Estudiante estudiante : estudiantes) {
            if (Objects.equals(estudiante.getId(), id)) {
                return estudiante;
            }
        }
        throw new EstudianteNoExisteException();
    }
}
