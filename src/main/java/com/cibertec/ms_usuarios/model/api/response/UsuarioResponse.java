package com.cibertec.ms_usuarios.model.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UsuarioResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer usuarioId;
    private String nombre;
    private String email;
    private String telefono;
    private LocalDateTime fechaRegistro;
    private List<DireccionResponse> direcciones;
    private List<RolResponse> roles;
}
