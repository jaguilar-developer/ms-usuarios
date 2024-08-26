package com.cibertec.ms_usuarios.model.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class RolResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer rolId;
    private String nombre;
    private String descripcion;

}
