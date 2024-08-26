package com.cibertec.ms_usuarios.controller;

import com.cibertec.ms_usuarios.model.api.request.RolRequest;
import com.cibertec.ms_usuarios.model.api.response.RolResponse;
import com.cibertec.ms_usuarios.service.impl.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolService rolService;

    @PostMapping("/guardarRol")
    public ResponseEntity<RolResponse> guardarRol(@RequestBody RolRequest request) {
        RolResponse rolResponse = rolService.guardarRol(request);
        return ResponseEntity.ok(rolResponse);
    }

    @PostMapping("/guardarRoles")
    public ResponseEntity<List<RolResponse>> guardarRoles(@RequestBody List<RolRequest> requests) {
        List<RolResponse> rolResponseList = rolService.guardarRoles(requests);
        return ResponseEntity.ok(rolResponseList);
    }

    @GetMapping("/obtenerRoles")
    public ResponseEntity<List<RolResponse>> obtenerRoles() {
        List<RolResponse> rolResponseList = rolService.obtenerRoles();
        return ResponseEntity.ok(rolResponseList);
    }

    @PatchMapping("/actualizarRol/{id}")
    public ResponseEntity<RolResponse> actualizarRol(@PathVariable Integer id, @RequestBody RolRequest request) {
        RolResponse rolResponse = rolService.actualizarRol(id, request);
        return ResponseEntity.ok(rolResponse);
    }


}
