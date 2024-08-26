package com.cibertec.ms_usuarios.service.transform;

import com.cibertec.ms_usuarios.model.api.response.RolResponse;
import com.cibertec.ms_usuarios.model.dto.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolResponseTransform {

    public RolResponse toResponse(Rol rol) {
        if (rol == null) {
            return null;
        }
        RolResponse response = new RolResponse();
        response.setRolId(rol.getRolId());
        response.setNombre(rol.getNombre());
        response.setDescripcion(rol.getDescripcion());

        return response;
    }
}
