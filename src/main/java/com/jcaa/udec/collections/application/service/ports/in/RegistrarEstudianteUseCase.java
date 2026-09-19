package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.CrearEstudianteComando;

public interface RegistrarEstudianteUseCase {
    void guardar(CrearEstudianteComando comando);
}
