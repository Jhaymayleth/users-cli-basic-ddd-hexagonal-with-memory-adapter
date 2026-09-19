package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEstudianteComando;
import com.jcaa.udec.collections.application.service.dto.command.CrearEstudianteComando;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerEstudianteConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.EliminarEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.RegistrarEstudianteUseCase;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarEstudiantePeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarEstudiantePeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerEstudianteResponse;
import com.jcaa.udec.collections.entrypoint.controller.mapper.EstudianteResponseMapper;

public class EstudianteControladorImpl implements EstudianteControlador {
    private final RegistrarEstudianteUseCase registrarUseCase;
    private final ObtenerEstudianteUseCase obtenerUseCase;
    private final ActualizarEstudianteUseCase actualizarUseCase;
    private final EliminarEstudianteUseCase eliminarUseCase;

    public EstudianteControladorImpl(
            RegistrarEstudianteUseCase registrarUseCase,
            ObtenerEstudianteUseCase obtenerUseCase,
            ActualizarEstudianteUseCase actualizarUseCase,
            EliminarEstudianteUseCase eliminarUseCase) {
        this.registrarUseCase = registrarUseCase;
        this.obtenerUseCase = obtenerUseCase;
        this.actualizarUseCase = actualizarUseCase;
        this.eliminarUseCase = eliminarUseCase;
    }

    @Override
    public void registrar(RegistrarEstudiantePeticion peticion) {
        CrearEstudianteComando comando = new CrearEstudianteComando(
                peticion.id(),
                peticion.nombre(),
                peticion.email(),
                peticion.documento(),
                peticion.categoria());
        registrarUseCase.guardar(comando);
    }

    @Override
    public ObtenerEstudianteResponse obtenerPorId(String id) {
        ObtenerEstudianteConsulta consulta = new ObtenerEstudianteConsulta(id);
        return EstudianteResponseMapper.mapearAResponse(obtenerUseCase.obtenerPorId(consulta));
    }

    @Override
    public ObtenerEstudianteResponse obtenerTodos() {
        return EstudianteResponseMapper.mapearAResponse(obtenerUseCase.obtenerTodos());
    }

    @Override
    public void actualizar(ActualizarEstudiantePeticion peticion) {
        ActualizarEstudianteComando comando = new ActualizarEstudianteComando(
                peticion.id(),
                peticion.nombre(),
                peticion.email(),
                peticion.documento(),
                peticion.categoria());
        actualizarUseCase.actualizar(comando);
    }

    @Override
    public void eliminar(String id) {
        eliminarUseCase.eliminarPorId(id);
    }
}
