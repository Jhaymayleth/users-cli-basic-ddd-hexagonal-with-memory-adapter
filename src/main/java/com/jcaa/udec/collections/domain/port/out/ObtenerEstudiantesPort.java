package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Estudiante;

import java.util.List;

public interface ObtenerEstudiantesPort {
    List<Estudiante> obtenerTodos();

    Estudiante buscarPorId(String id);
}
