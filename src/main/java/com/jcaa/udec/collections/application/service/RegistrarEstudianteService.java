package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.CrearEstudianteComando;
import com.jcaa.udec.collections.application.service.mapper.EstudianteMapper;
import com.jcaa.udec.collections.application.service.ports.in.RegistrarEstudianteUseCase;
import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.domain.port.out.GuardarEstudiantePort;

public class RegistrarEstudianteService implements RegistrarEstudianteUseCase {
    private final GuardarEstudiantePort guardarEstudiantePort;

    public RegistrarEstudianteService(GuardarEstudiantePort guardarEstudiantePort) {
        this.guardarEstudiantePort = guardarEstudiantePort;
    }

    @Override
    public void guardar(CrearEstudianteComando comando) {
        Estudiante estudiante = EstudianteMapper.mapearAEstudiante(comando);
        guardarEstudiantePort.guardar(estudiante);
    }
}
