package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerEstudianteConsulta;
import com.jcaa.udec.collections.domain.core.model.Estudiante;

import java.util.List;

public interface ObtenerEstudianteUseCase {
    List<Estudiante> obtenerTodos();

    Estudiante obtenerPorId(ObtenerEstudianteConsulta consulta);
}
