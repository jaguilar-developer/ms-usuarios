package com.cibertec.ms_usuarios.service.impl;

import com.cibertec.ms_usuarios.model.api.request.RolRequest;
import com.cibertec.ms_usuarios.model.api.response.RolResponse;
import com.cibertec.ms_usuarios.model.dto.Rol;
import com.cibertec.ms_usuarios.repository.RolRepository;
import com.cibertec.ms_usuarios.service.transform.RolRequestTransform;
import com.cibertec.ms_usuarios.service.transform.RolResponseTransform;
import com.cibertec.ms_usuarios.util.error.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolService {

    private final RolRepository rolRepository;
    private final RolRequestTransform requestTransform;
    private final RolResponseTransform responseTransform;

    @Transactional
    public RolResponse guardarRol(RolRequest request) {
        Rol rol = requestTransform.toRol(request);
        Rol rolGuardado = rolRepository.save(rol);
        return responseTransform.toResponse(rolGuardado);
    }

    @Transactional
    public List<RolResponse> guardarRoles(List<RolRequest> requestList) {
        List<Rol> rolesOrdenados = requestList.stream()
                .map(requestTransform::toRol)
                .sorted(Comparator.comparing(Rol::getNombre)).toList();

        List<Rol> rolesGuardados = rolesOrdenados.stream()
                .map(rolRepository::save).toList();

        return rolesGuardados.stream()
                .map(responseTransform::toResponse)
                .collect(Collectors.toList());
    }

    public List<RolResponse> obtenerRoles() {
        return rolRepository.findAll().stream()
                .map(responseTransform::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public RolResponse actualizarRol(Integer id, RolRequest request) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontro el valor buscado: " + id));

        if (request.getNombre() != null) {
            rol.setNombre(request.getNombre());
        }
        if (request.getDescripcion() != null) {
            rol.setDescripcion(request.getDescripcion());
        }

        Rol rolActualizado = rolRepository.save(rol);

        return responseTransform.toResponse(rolActualizado);
    }


}
