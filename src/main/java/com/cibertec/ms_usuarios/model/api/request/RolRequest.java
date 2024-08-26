package com.cibertec.ms_usuarios.model.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class RolRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String descripcion;
}
