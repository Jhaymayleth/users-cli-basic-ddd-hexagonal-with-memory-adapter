package com.jcaa.udec.collections.application.service.mapper;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEstudianteComando;
import com.jcaa.udec.collections.application.service.dto.command.CrearEstudianteComando;
import com.jcaa.udec.collections.domain.core.model.Estudiante;

public final class EstudianteMapper {
    private EstudianteMapper() {}

    public static Estudiante mapearAEstudiante(CrearEstudianteComando comando) {
        return Estudiante.builder()
                .id(comando.id())
                .nombre(comando.nombre())
                .email(comando.email())
                .documento(comando.documento())
                .categoria(comando.categoria())
                .build();
    }

    public static Estudiante mapearAEstudiante(ActualizarEstudianteComando comando) {
        return Estudiante.builder()
                .id(comando.id())
                .nombre(comando.nombre())
                .email(comando.email())
                .documento(comando.documento())
                .categoria(comando.categoria())
                .build();
    }
}
