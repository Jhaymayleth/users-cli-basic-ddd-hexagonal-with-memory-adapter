package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.valueobject.CategoriaLicencia;
import com.jcaa.udec.collections.domain.core.valueobject.DocumentoIdentidad;
import com.jcaa.udec.collections.domain.core.valueobject.Email;
import com.jcaa.udec.collections.domain.core.valueobject.EstudianteId;
import com.jcaa.udec.collections.domain.core.valueobject.NombreCompleto;
import lombok.Builder;

public class Estudiante {
    private final EstudianteId id;
    private final NombreCompleto nombre;
    private final Email email;
    private final DocumentoIdentidad documento;
    private final CategoriaLicencia categoria;

    @Builder
    public Estudiante(String id, String nombre, String email, String documento, String categoria) {
        this.id = new EstudianteId(id);
        this.nombre = new NombreCompleto(nombre);
        this.email = new Email(email);
        this.documento = new DocumentoIdentidad(documento);
        this.categoria = new CategoriaLicencia(categoria);
    }

    public String getId() {
        return id.valor();
    }

    public String getNombre() {
        return nombre.valor();
    }

    public String getEmail() {
        return email.valor();
    }

    public String getDocumento() {
        return documento.valor();
    }

    public String getCategoria() {
        return categoria.valor();
    }
}
