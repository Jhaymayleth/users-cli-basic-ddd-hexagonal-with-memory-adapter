package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.EstudianteNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.EliminarEstudiantePort;

import java.util.List;
import java.util.Objects;

public class EliminarEstudianteAdapter implements EliminarEstudiantePort {
    private final List<Estudiante> estudiantes = EstudiantesMemoria.obtenerEstudiantes();

    @Override
    public void eliminarPorId(String id) {
        for (Estudiante estudiante : List.copyOf(estudiantes)) {
            if (Objects.equals(estudiante.getId(), id)) {
                estudiantes.remove(estudiante);
                return;
            }
        }
        throw new EstudianteNoExisteException();
    }
}
