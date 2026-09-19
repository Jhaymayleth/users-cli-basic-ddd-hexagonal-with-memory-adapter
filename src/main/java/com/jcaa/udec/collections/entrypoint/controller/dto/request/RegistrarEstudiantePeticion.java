package com.jcaa.udec.collections.entrypoint.controller.dto.request;

public record RegistrarEstudiantePeticion(
        String id, String nombre, String email, String documento, String categoria) {
}
