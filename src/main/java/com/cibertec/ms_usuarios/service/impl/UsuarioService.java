package com.cibertec.ms_usuarios.service.impl;

import com.cibertec.ms_usuarios.model.api.request.DireccionRequest;
import com.cibertec.ms_usuarios.model.api.request.UsuarioRequest;
import com.cibertec.ms_usuarios.model.api.response.UsuarioResponse;
import com.cibertec.ms_usuarios.model.dto.Direccion;
import com.cibertec.ms_usuarios.model.dto.Usuario;
import com.cibertec.ms_usuarios.repository.UsuarioRepository;
import com.cibertec.ms_usuarios.service.transform.UsuarioRequestTransform;
import com.cibertec.ms_usuarios.service.transform.UsuarioResponseTransform;
import com.cibertec.ms_usuarios.util.PasswordUtil;
import com.cibertec.ms_usuarios.util.error.EntityNotFoundException;
import com.cibertec.ms_usuarios.util.error.PasswordInvalidException;
import com.cibertec.ms_usuarios.util.error.ValueInvalidException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioRequestTransform requestTransform;
    private final UsuarioResponseTransform responseTransform;

    @Transactional
    public UsuarioResponse guardarUsuario(UsuarioRequest request) {
        Usuario usuario = requestTransform.toUsuario(request);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return responseTransform.toResponse(usuarioGuardado);
    }

    @Transactional
    public UsuarioResponse agregarDireccion(Integer idCliente, DireccionRequest request) {
        Usuario usuario = usuarioRepository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el cliente con ID: " + idCliente));

        Direccion newDireccion = requestTransform.toDireccion(request, usuario);
        usuario.getDirecciones().add(newDireccion);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return responseTransform.toResponse(usuarioGuardado);
    }

    @Transactional
    public UsuarioResponse deshabilitarDireccion(Integer id, Integer idDireccion) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        Direccion direccion = usuario.getDirecciones().stream()
                .filter(d -> d.getDireccionId().equals(idDireccion))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Dirección no encontrada con ID: " + idDireccion));

        direccion.setActivo(false);
        usuarioRepository.save(usuario);

        return responseTransform.toResponse(usuario);
    }

    public UsuarioResponse authenticate(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el usuario con correo: " + email));

        if (PasswordUtil.verifyPassword(password, usuario.getPassword())) {
            return responseTransform.toResponse(usuario);
        } else {
            throw new PasswordInvalidException("La contraseña es incorrecta");
        }
    }

    public UsuarioResponse buscarUsuario(String tipoBusqueda, String valorBuscado) {
        Usuario usuarioEncontrado;
        switch (tipoBusqueda) {
            case "id" -> usuarioEncontrado =
                    usuarioRepository.findById(Integer.valueOf(valorBuscado))
                            .orElseThrow(() -> new EntityNotFoundException("No se encontro el usuario con ID: " + valorBuscado));
            case "email" -> usuarioEncontrado =
                    usuarioRepository.findByEmail(valorBuscado)
                            .orElseThrow(() -> new EntityNotFoundException("No se encontro el usuario con correo: " + valorBuscado));
            default -> throw new ValueInvalidException("Tipo de busqueda no soportado");
        }
        return responseTransform.toResponse(usuarioEncontrado);
    }

    public UsuarioResponse editarUsuario(Integer id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        if (request.getNombre() != null) {
            usuario.setNombre(request.getNombre());
        }

        if (request.getEmail() != null) {
            usuario.setEmail(request.getEmail());
        }

        if (request.getPassword() != null) {
            usuario.setPassword(request.getPassword());
        }

        if (request.getTelefono() != null) {
            usuario.setTelefono(request.getTelefono());
        }

        return responseTransform.toResponse(usuarioRepository.save(usuario));
    }

    public void eliminarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        usuarioRepository.delete(usuario);
    }
}
