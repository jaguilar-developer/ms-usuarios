package com.cibertec.ms_usuarios.model.api.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UsuarioRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private List<DireccionRequest> direcciones;
    private List<Integer> rolesIds;

}
