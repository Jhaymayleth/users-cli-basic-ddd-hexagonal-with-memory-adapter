package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Estudiante;

import java.util.ArrayList;
import java.util.List;

final class EstudiantesMemoria {
    private static final List<Estudiante> ESTUDIANTES = new ArrayList<>();

    private EstudiantesMemoria() {
    }

    static List<Estudiante> obtenerEstudiantes() {
        return ESTUDIANTES;
    }
}
