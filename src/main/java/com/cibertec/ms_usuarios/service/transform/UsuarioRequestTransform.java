package com.cibertec.ms_usuarios.service.transform;

import com.cibertec.ms_usuarios.model.api.request.DireccionRequest;
import com.cibertec.ms_usuarios.model.api.request.UsuarioRequest;
import com.cibertec.ms_usuarios.model.dto.Direccion;
import com.cibertec.ms_usuarios.model.dto.Rol;
import com.cibertec.ms_usuarios.model.dto.Usuario;
import com.cibertec.ms_usuarios.repository.RolRepository;
import com.cibertec.ms_usuarios.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UsuarioRequestTransform {

    @Autowired
    private RolRepository rolRepository;

    public Usuario toUsuario(UsuarioRequest request) {
        Usuario objUsuario = new Usuario();

        if (request != null) {
            objUsuario.setNombre(request.getNombre());
            objUsuario.setEmail(request.getEmail());
            objUsuario.setPassword(PasswordUtil.hashPassword(request.getPassword()));
            objUsuario.setTelefono(request.getTelefono());
            objUsuario.setFechaRegistro(LocalDateTime.now());

            if (request.getDirecciones() != null && !request.getDirecciones().isEmpty()) {
                Set<Direccion> direccionesSet = request.getDirecciones().stream()
                        .map(direccionRequest -> toDireccion(direccionRequest, objUsuario))
                        .collect(Collectors.toSet());
                objUsuario.setDirecciones(direccionesSet);
            }

            if (request.getRolesIds() != null && !request.getRolesIds().isEmpty()) {
                Set<Rol> rolesSet = request.getRolesIds().stream()
                        .map(this::findRolById)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());
                objUsuario.setRoles(rolesSet);
            }
        }

        return objUsuario;
    }


    public Direccion toDireccion(DireccionRequest direccionRequest, Usuario usuario) {
        Direccion direccion = new Direccion();
        if (direccionRequest != null) {
            direccion.setDireccion(direccionRequest.getDireccion());
            direccion.setCiudad(direccionRequest.getCiudad());
            direccion.setEstado(direccionRequest.getEstado());
            direccion.setCodigoPostal(direccionRequest.getCodigoPostal());
            direccion.setPais(direccionRequest.getPais());
            direccion.setActivo(true);
            direccion.setUsuario(usuario);
        }
        return direccion;
    }


    private Rol findRolById(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }
}
