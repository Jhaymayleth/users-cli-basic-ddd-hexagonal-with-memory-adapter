package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.EstudianteYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.GuardarEstudiantePort;

import java.util.List;
import java.util.Objects;

public class GuardarEstudianteAdapter implements GuardarEstudiantePort {
    private final List<Estudiante> estudiantes = EstudiantesMemoria.obtenerEstudiantes();

    @Override
    public void guardar(Estudiante estudiante) {
        for (Estudiante registrado : estudiantes) {
            if (Objects.equals(registrado.getId(), estudiante.getId())) {
                throw new EstudianteYaExisteException();
            }
        }
        estudiantes.add(estudiante);
    }
}
