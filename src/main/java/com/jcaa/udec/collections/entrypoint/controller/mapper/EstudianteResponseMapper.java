package com.jcaa.udec.collections.entrypoint.controller.mapper;

import com.jcaa.udec.collections.domain.core.model.Estudiante;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.EstudianteResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerEstudianteResponse;
import java.util.List;

public final class EstudianteResponseMapper {
    private EstudianteResponseMapper() {
    }

    public static ObtenerEstudianteResponse mapearAResponse(Estudiante estudiante) {
        return new ObtenerEstudianteResponse(List.of(mapearAResponseEstudiante(estudiante)));
    }

    public static ObtenerEstudianteResponse mapearAResponse(List<Estudiante> estudiantes) {
        return new ObtenerEstudianteResponse(estudiantes.stream()
                .map(EstudianteResponseMapper::mapearAResponseEstudiante)
                .toList());
    }

    private static EstudianteResponse mapearAResponseEstudiante(Estudiante estudiante) {
        return EstudianteResponse.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .email(estudiante.getEmail())
                .documento(estudiante.getDocumento())
                .categoria(estudiante.getCategoria())
                .build();
    }
}
