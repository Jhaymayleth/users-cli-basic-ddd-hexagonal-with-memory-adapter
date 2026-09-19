package com.jcaa.udec;

import com.jcaa.udec.collections.adapter.persistence.memory.ActualizarEstudianteAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.EliminarEstudianteAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.GuardarEstudianteAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.ObtenerEstudiantesAdapter;
import com.jcaa.udec.collections.application.service.ActualizarEstudianteService;
import com.jcaa.udec.collections.application.service.EliminarEstudianteService;
import com.jcaa.udec.collections.application.service.ObtenerEstudiantesService;
import com.jcaa.udec.collections.application.service.RegistrarEstudianteService;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.EliminarEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerEstudianteUseCase;
import com.jcaa.udec.collections.application.service.ports.in.RegistrarEstudianteUseCase;
import com.jcaa.udec.collections.domain.port.out.ActualizarEstudiantePort;
import com.jcaa.udec.collections.domain.port.out.EliminarEstudiantePort;
import com.jcaa.udec.collections.domain.port.out.GuardarEstudiantePort;
import com.jcaa.udec.collections.domain.port.out.ObtenerEstudiantesPort;
import com.jcaa.udec.collections.entrypoint.cli.EstudianteCli;
import com.jcaa.udec.collections.entrypoint.controller.EstudianteControlador;
import com.jcaa.udec.collections.entrypoint.controller.EstudianteControladorImpl;

public class MainEstudiante {
    public static void main(String[] args) {
        GuardarEstudianteAdapter guardarAdapter = new GuardarEstudianteAdapter();
        ObtenerEstudiantesAdapter obtenerAdapter = new ObtenerEstudiantesAdapter();
        ActualizarEstudianteAdapter actualizarAdapter = new ActualizarEstudianteAdapter();
        EliminarEstudianteAdapter eliminarAdapter = new EliminarEstudianteAdapter();
        GuardarEstudiantePort guardarPort = guardarAdapter;
        ObtenerEstudiantesPort obtenerPort = obtenerAdapter;
        ActualizarEstudiantePort actualizarPort = actualizarAdapter;
        EliminarEstudiantePort eliminarPort = eliminarAdapter;
        RegistrarEstudianteUseCase registrarUseCase = new RegistrarEstudianteService(guardarPort);
        ObtenerEstudianteUseCase obtenerUseCase = new ObtenerEstudiantesService(obtenerPort);
        ActualizarEstudianteUseCase actualizarUseCase = new ActualizarEstudianteService(actualizarPort);
        EliminarEstudianteUseCase eliminarUseCase = new EliminarEstudianteService(eliminarPort);
        EstudianteControlador controlador = new EstudianteControladorImpl(
                registrarUseCase, obtenerUseCase, actualizarUseCase, eliminarUseCase);
        EstudianteCli cli = new EstudianteCli(controlador);
        cli.ejecutarAccion();
    }
}
