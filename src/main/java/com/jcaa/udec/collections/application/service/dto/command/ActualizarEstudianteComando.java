package com.jcaa.udec.collections.application.service.dto.command;

public record ActualizarEstudianteComando(
        String id, String nombre, String email, String documento, String categoria) {}
