package com.jcaa.udec.collections.entrypoint.cli;

import com.jcaa.udec.collections.domain.core.exception.EstudianteInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.EstudianteNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.EstudianteYaExisteException;
import com.jcaa.udec.collections.domain.core.valueobject.CategoriaLicencia;
import com.jcaa.udec.collections.domain.core.valueobject.DocumentoIdentidad;
import com.jcaa.udec.collections.domain.core.valueobject.Email;
import com.jcaa.udec.collections.domain.core.valueobject.EstudianteId;
import com.jcaa.udec.collections.domain.core.valueobject.NombreCompleto;
import com.jcaa.udec.collections.entrypoint.controller.EstudianteControlador;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarEstudiantePeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarEstudiantePeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerEstudianteResponse;

import java.util.Scanner;

public class EstudianteCli {
    private static final int OPCION_AGREGAR = 1;
    private static final int OPCION_BUSCAR = 2;
    private static final int OPCION_MOSTRAR_TODOS = 3;
    private static final int OPCION_ACTUALIZAR = 4;
    private static final int OPCION_ELIMINAR = 5;
    private static final int OPCION_SALIR = 6;
    private static final String TEXTO_TITULO = "** CEA - GESTION DE ESTUDIANTES (HEXAGONAL) **";
    private static final String SEPARADOR = "- - - - - - - - - ";
    private static final String TEXTO_OPCION_AGREGAR = "1 - Agregar estudiante";
    private static final String TEXTO_OPCION_BUSCAR = "2 - Buscar por Id";
    private static final String TEXTO_OPCION_MOSTRAR_TODOS = "3 - Ver todos";
    private static final String TEXTO_OPCION_ACTUALIZAR = "4 - Actualizar";
    private static final String TEXTO_OPCION_ELIMINAR = "5 - Eliminar";
    private static final String TEXTO_OPCION_SALIR = "6 - Salir";
    private static final String TEXTO_SOLICITUD_OPCION = "Ingrese el numero de la opcion: ";
    private static final String SOLICITUD_ID = "ID: ";
    private static final String SOLICITUD_NOMBRE = "NOMBRE COMPLETO: ";
    private static final String SOLICITUD_EMAIL = "EMAIL: ";
    private static final String SOLICITUD_DOCUMENTO = "DOCUMENTO (6-12 digitos): ";
    private static final String SOLICITUD_CATEGORIA = "CATEGORIA (A1/A2/B1/C1): ";
    private static final String MENSAJE_OPCION_INVALIDA = "Opcion [%s] invalida";
    private static final String MENSAJE_ID_INVALIDO = "ID INVALIDO: debe ser un numero entero";
    private static final String MENSAJE_NOMBRE_INVALIDO = "NOMBRE INVALIDO: minimo 3 caracteres";
    private static final String MENSAJE_EMAIL_INVALIDO = "EMAIL INVALIDO: ingrese un correo valido";
    private static final String MENSAJE_DOCUMENTO_INVALIDO = "DOCUMENTO INVALIDO: 6-12 digitos numericos";
    private static final String MENSAJE_CATEGORIA_INVALIDA = "CATEGORIA INVALIDA: use A1, A2, B1 o C1";
    private static final String MENSAJE_ERROR = "ERROR: ";
    private static final String MENSAJE_REGISTRO_EXITOSO = "Estudiante registrado correctamente.";
    private static final String MENSAJE_ACTUALIZACION_EXITOSA = "Estudiante actualizado correctamente.";
    private static final String MENSAJE_ELIMINACION_EXITOSA = "Estudiante eliminado correctamente.";
    private static final String MENSAJE_LISTA_VACIA = "No hay estudiantes registrados.";
    private static final String MENSAJE_DESPEDIDA = "Esperamos tu regreso. Bye, Bye";
    private static final String MARCA_ORDEN_BYTES = "\uFEFF";
    private static final String TEXTO_VACIO = "";
    private final EstudianteControlador estudianteControlador;
    private final Scanner entrada;

    public EstudianteCli(EstudianteControlador estudianteControlador) {
        this(estudianteControlador, new Scanner(System.in));
    }

    EstudianteCli(EstudianteControlador estudianteControlador, Scanner entrada) {
        this.estudianteControlador = estudianteControlador;
        this.entrada = entrada;
    }

    public void ejecutarAccion() {
        boolean continuar = true;
        while (continuar) {
            int opcion = obtenerOpcionMenu();
            try {
                switch (opcion) {
                    case OPCION_AGREGAR -> registrarEstudiante();
                    case OPCION_BUSCAR -> mostrarEstudiantePorId();
                    case OPCION_MOSTRAR_TODOS -> mostrarTodos();
                    case OPCION_ACTUALIZAR -> actualizarEstudiante();
                    case OPCION_ELIMINAR -> eliminarEstudiante();
                    case OPCION_SALIR -> continuar = false;
                }
            } catch (EstudianteInvalidoException
                    | EstudianteNoExisteException
                    | EstudianteYaExisteException exception) {
                System.out.println(MENSAJE_ERROR + exception.getMessage());
            }
        }
        System.out.println(MENSAJE_DESPEDIDA);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println(TEXTO_TITULO);
        System.out.println(SEPARADOR);
        System.out.println(TEXTO_OPCION_AGREGAR);
        System.out.println(TEXTO_OPCION_BUSCAR);
        System.out.println(TEXTO_OPCION_MOSTRAR_TODOS);
        System.out.println(TEXTO_OPCION_ACTUALIZAR);
        System.out.println(TEXTO_OPCION_ELIMINAR);
        System.out.println(TEXTO_OPCION_SALIR);
        System.out.print(TEXTO_SOLICITUD_OPCION);
    }

    int obtenerOpcionMenu() {
        do {
            mostrarMenu();
            String valorIngresado = limpiarEntrada(entrada.nextLine());
            try {
                int opcion = Integer.parseInt(valorIngresado);
                if (opcion >= OPCION_AGREGAR && opcion <= OPCION_SALIR) {
                    return opcion;
                }
            } catch (NumberFormatException exception) {
                // Informa valor invalido y repite menu.
            }
            System.out.printf(MENSAJE_OPCION_INVALIDA + "%n", valorIngresado);
        } while (true);
    }

    private void registrarEstudiante() {
        System.out.println();
        estudianteControlador.registrar(new RegistrarEstudiantePeticion(
                capturarId(), capturarNombre(), capturarEmail(), capturarDocumento(), capturarCategoria()));
        System.out.println(MENSAJE_REGISTRO_EXITOSO);
    }

    private void actualizarEstudiante() {
        System.out.println();
        estudianteControlador.actualizar(new ActualizarEstudiantePeticion(
                capturarId(), capturarNombre(), capturarEmail(), capturarDocumento(), capturarCategoria()));
        System.out.println(MENSAJE_ACTUALIZACION_EXITOSA);
    }

    private void eliminarEstudiante() {
        estudianteControlador.eliminar(capturarId());
        System.out.println(MENSAJE_ELIMINACION_EXITOSA);
    }

    private void mostrarEstudiantePorId() {
        System.out.println(estudianteControlador.obtenerPorId(capturarId()));
    }

    private void mostrarTodos() {
        ObtenerEstudianteResponse response = estudianteControlador.obtenerTodos();
        if (response.estaVacia()) {
            System.out.println(MENSAJE_LISTA_VACIA);
            return;
        }
        System.out.println(response);
    }

    private String capturarId() {
        do {
            System.out.print(SOLICITUD_ID);
            String id = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new EstudianteId(id))) {
                return id;
            }
            System.out.println(MENSAJE_ID_INVALIDO);
        } while (true);
    }

    private String capturarNombre() {
        do {
            System.out.print(SOLICITUD_NOMBRE);
            String nombre = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new NombreCompleto(nombre))) {
                return nombre;
            }
            System.out.println(MENSAJE_NOMBRE_INVALIDO);
        } while (true);
    }

    private String capturarEmail() {
        do {
            System.out.print(SOLICITUD_EMAIL);
            String email = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new Email(email))) {
                return email;
            }
            System.out.println(MENSAJE_EMAIL_INVALIDO);
        } while (true);
    }

    private String capturarDocumento() {
        do {
            System.out.print(SOLICITUD_DOCUMENTO);
            String documento = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new DocumentoIdentidad(documento))) {
                return documento;
            }
            System.out.println(MENSAJE_DOCUMENTO_INVALIDO);
        } while (true);
    }

    private String capturarCategoria() {
        do {
            System.out.print(SOLICITUD_CATEGORIA);
            String categoria = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new CategoriaLicencia(categoria))) {
                return categoria;
            }
            System.out.println(MENSAJE_CATEGORIA_INVALIDA);
        } while (true);
    }

    private static String limpiarEntrada(String valor) {
        return valor.replace(MARCA_ORDEN_BYTES, TEXTO_VACIO).trim();
    }

    private static boolean esValido(Runnable validacion) {
        try {
            validacion.run();
            return true;
        } catch (EstudianteInvalidoException exception) {
            return false;
        }
    }
}
