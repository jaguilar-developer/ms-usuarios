package com.cibertec.ms_usuarios.model.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class DireccionRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String direccion;
    private String ciudad;
    private String estado;
    private String codigoPostal;
    private String pais;
}
