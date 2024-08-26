package com.cibertec.ms_usuarios.service.transform;

import com.cibertec.ms_usuarios.model.api.request.RolRequest;
import com.cibertec.ms_usuarios.model.dto.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolRequestTransform {

    public Rol toRol(RolRequest request) {
        Rol objRol = new Rol();

        if (request != null) {
            objRol.setNombre(request.getNombre());
            objRol.setDescripcion(request.getDescripcion());
        }

        return objRol;
    }
}
