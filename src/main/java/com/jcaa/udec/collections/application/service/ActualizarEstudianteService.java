package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEstudianteComando;
import com.jcaa.udec.collections.application.service.mapper.EstudianteMapper;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarEstudianteUseCase;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.ActualizarEstudiantePort;

public class ActualizarEstudianteService implements ActualizarEstudianteUseCase {
    private final ActualizarEstudiantePort actualizarEstudiantePort;

    public ActualizarEstudianteService(ActualizarEstudiantePort actualizarEstudiantePort) {
        this.actualizarEstudiantePort = actualizarEstudiantePort;
    }

    @Override
    public void actualizar(ActualizarEstudianteComando comando) {
        Estudiante estudiante = EstudianteMapper.mapearAEstudiante(comando);
        actualizarEstudiantePort.actualizar(estudiante);
    }
}
