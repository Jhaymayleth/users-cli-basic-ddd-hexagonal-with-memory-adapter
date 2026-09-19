package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.EstudianteNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.ActualizarEstudiantePort;

import java.util.List;
import java.util.Objects;

public class ActualizarEstudianteAdapter implements ActualizarEstudiantePort {
    private final List<Estudiante> estudiantes = EstudiantesMemoria.obtenerEstudiantes();

    @Override
    public void actualizar(Estudiante estudiante) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (Objects.equals(estudiantes.get(i).getId(), estudiante.getId())) {
                estudiantes.set(i, estudiante);
                return;
            }
        }
        throw new EstudianteNoExisteException();
    }
}
