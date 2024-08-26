package com.cibertec.ms_usuarios.service.transform;

import com.cibertec.ms_usuarios.model.api.response.DireccionResponse;
import com.cibertec.ms_usuarios.model.api.response.RolResponse;
import com.cibertec.ms_usuarios.model.api.response.UsuarioResponse;
import com.cibertec.ms_usuarios.model.dto.Direccion;
import com.cibertec.ms_usuarios.model.dto.Rol;
import com.cibertec.ms_usuarios.model.dto.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UsuarioResponseTransform {

    public UsuarioResponse toResponse(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioResponse response = new UsuarioResponse();
        response.setUsuarioId(usuario.getUsuarioId());
        response.setNombre(usuario.getNombre());
        response.setEmail(usuario.getEmail());
        response.setTelefono(usuario.getTelefono());
        response.setFechaRegistro(usuario.getFechaRegistro());
        response.setDirecciones(convertDirecciones(usuario.getDirecciones()));
        response.setRoles(convertRoles(usuario.getRoles()));
        return response;
    }

    private List<DireccionResponse> convertDirecciones(Set<Direccion> direcciones) {
        if (direcciones == null) {
            return null;
        }
        return direcciones.stream()
                .map(this::toDireccionResponse)
                .collect(Collectors.toList());
    }

    private DireccionResponse toDireccionResponse(Direccion direccion) {
        if (direccion == null) {
            return null;
        }
        DireccionResponse response = new DireccionResponse();
        response.setDireccionId(direccion.getDireccionId());
        response.setDireccion(direccion.getDireccion());
        response.setCiudad(direccion.getCiudad());
        response.setEstado(direccion.getEstado());
        response.setCodigoPostal(direccion.getCodigoPostal());
        response.setPais(direccion.getPais());
        response.setActivo(direccion.getActivo());
        return response;
    }

    private List<RolResponse> convertRoles(Set<Rol> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(this::toRolResponse)
                .collect(Collectors.toList());
    }

    private RolResponse toRolResponse(Rol rol) {
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
