package com.cibertec.ms_usuarios.model.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DireccionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer direccionId;
    private String direccion;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String pais;
    private Boolean activo;

}
