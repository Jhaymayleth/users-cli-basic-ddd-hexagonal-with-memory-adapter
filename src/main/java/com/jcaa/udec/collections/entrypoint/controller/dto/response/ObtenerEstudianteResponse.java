package com.jcaa.udec.collections.entrypoint.controller.dto.response;

import java.util.List;

public record ObtenerEstudianteResponse(List<EstudianteResponse> estudiantes) {
    public ObtenerEstudianteResponse {
        estudiantes = List.copyOf(estudiantes);
    }

    public boolean estaVacia() {
        return estudiantes.isEmpty();
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), estudiantes.stream()
                .map(EstudianteResponse::toString)
                .toList());
    }
}
