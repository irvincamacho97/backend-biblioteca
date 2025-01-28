package com.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

    @Query("SELECT COUNT(*) FROM UsuarioEntity u WHERE u.correo = :correo")
	Integer existsByCorreo(@Param("correo") String correo);

    @Query("SELECT u FROM UsuarioEntity u WHERE u.estatus = true")
    Page<UsuarioEntity> obtenerListaActivos(Pageable pageable); 

    @Query("SELECT u FROM UsuarioEntity u WHERE u.estatus = true AND u.idUsuario = :idUsuario")
    Optional<UsuarioEntity> obtenerUsuarioActivo(Integer idUsuario);

}
