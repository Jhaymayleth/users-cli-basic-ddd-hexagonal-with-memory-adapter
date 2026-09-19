package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerEstudianteConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerEstudianteUseCase;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.ObtenerEstudiantesPort;

import java.util.List;

public class ObtenerEstudiantesService implements ObtenerEstudianteUseCase {
    private final ObtenerEstudiantesPort obtenerEstudiantesPort;

    public ObtenerEstudiantesService(ObtenerEstudiantesPort obtenerEstudiantesPort) {
        this.obtenerEstudiantesPort = obtenerEstudiantesPort;
    }

    @Override
    public List<Estudiante> obtenerTodos() {
        return obtenerEstudiantesPort.obtenerTodos();
    }

    @Override
    public Estudiante obtenerPorId(ObtenerEstudianteConsulta consulta) {
        return obtenerEstudiantesPort.buscarPorId(consulta.id());
    }
}
