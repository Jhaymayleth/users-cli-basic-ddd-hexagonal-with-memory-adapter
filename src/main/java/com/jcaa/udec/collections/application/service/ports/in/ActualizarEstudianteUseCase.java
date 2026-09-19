package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEstudianteComando;

public interface ActualizarEstudianteUseCase {
    void actualizar(ActualizarEstudianteComando comando);
}
