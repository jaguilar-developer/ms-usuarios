package com.cibertec.ms_usuarios.controller;

import com.cibertec.ms_usuarios.model.api.request.DireccionRequest;
import com.cibertec.ms_usuarios.model.api.request.UsuarioRequest;
import com.cibertec.ms_usuarios.model.api.response.UsuarioResponse;
import com.cibertec.ms_usuarios.service.impl.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/guardarUsuario")
    public ResponseEntity<UsuarioResponse> guardarUsuario(@RequestBody UsuarioRequest request) {
        UsuarioResponse usuarioResponse = usuarioService.guardarUsuario(request);
        return ResponseEntity.ok(usuarioResponse);
    }

    @PatchMapping("/agregarDireccion/{id}")
    public ResponseEntity<UsuarioResponse> agregarDireccion(@PathVariable Integer id, @RequestBody DireccionRequest request) {
        UsuarioResponse usuarioResponse = usuarioService.agregarDireccion(id, request);
        return ResponseEntity.ok(usuarioResponse);
    }

    @PatchMapping("/deshabilitarDireccion/{id}/{idDireccion}")
    public ResponseEntity<UsuarioResponse> deshabilitarDireccion(@PathVariable Integer id, @PathVariable Integer idDireccion) {
        UsuarioResponse usuarioResponse = usuarioService.deshabilitarDireccion(id, idDireccion);
        return ResponseEntity.ok(usuarioResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> authenticateUser(@RequestBody UsuarioRequest request) {
        UsuarioResponse isAuthenticated = usuarioService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(isAuthenticated);
    }

    @GetMapping("/buscarUsuario")
    public ResponseEntity<UsuarioResponse> buscarUsuario(
            @RequestParam("tipoBusqueda") String tipoBusqueda,
            @RequestParam("valorBusqueda") String valorBusqueda) {
        UsuarioResponse usuarioResponse = usuarioService.buscarUsuario(tipoBusqueda, valorBusqueda);
        return ResponseEntity.ok(usuarioResponse);
    }

    @PatchMapping("/editarUsuario/{id}")
    public ResponseEntity<UsuarioResponse> editarUsuario(@PathVariable Integer id, @RequestBody UsuarioRequest request) {
        UsuarioResponse usuarioResponse = usuarioService.editarUsuario(id, request);
        return ResponseEntity.ok(usuarioResponse);
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.accepted().build();
    }
}
