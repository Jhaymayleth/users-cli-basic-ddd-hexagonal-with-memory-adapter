package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.ports.in.EliminarEstudianteUseCase;
import com.jcaa.udec.collections.domain.port.out.EliminarEstudiantePort;

public class EliminarEstudianteService implements EliminarEstudianteUseCase {
    private final EliminarEstudiantePort eliminarEstudiantePort;

    public EliminarEstudianteService(EliminarEstudiantePort eliminarEstudiantePort) {
        this.eliminarEstudiantePort = eliminarEstudiantePort;
    }

    @Override
    public void eliminarPorId(String id) {
        eliminarEstudiantePort.eliminarPorId(id);
    }
}
