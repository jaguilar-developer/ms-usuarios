package com.cibertec.ms_usuarios.repository;

import com.cibertec.ms_usuarios.model.dto.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByNombre(String nombre);

}
