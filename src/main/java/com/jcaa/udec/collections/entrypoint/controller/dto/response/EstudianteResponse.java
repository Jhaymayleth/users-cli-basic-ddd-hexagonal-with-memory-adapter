package com.jcaa.udec.collections.entrypoint.controller.dto.response;

import lombok.Builder;

@Builder
public record EstudianteResponse(String id, String nombre, String email, String documento, String categoria) {
    private static final String FORMATO_DATOS = """
            ID: %s
            NOMBRE: %s
            EMAIL: %s
            DOCUMENTO: %s
            CATEGORIA: %s
            """;

    @Override
    public String toString() {
        return FORMATO_DATOS.formatted(id, nombre, email, documento, categoria);
    }
}
