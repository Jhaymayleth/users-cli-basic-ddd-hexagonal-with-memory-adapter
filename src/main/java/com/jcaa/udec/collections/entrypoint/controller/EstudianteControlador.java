package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarEstudiantePeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarEstudiantePeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerEstudianteResponse;

public interface EstudianteControlador {
    void registrar(RegistrarEstudiantePeticion peticion);

    ObtenerEstudianteResponse obtenerPorId(String id);

    ObtenerEstudianteResponse obtenerTodos();

    void actualizar(ActualizarEstudiantePeticion peticion);

    void eliminar(String id);
}
