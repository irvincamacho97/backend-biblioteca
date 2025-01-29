package com.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.LibroEntity;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity,Integer>{

    @Query("SELECT l FROM LibroEntity l WHERE l.estatus = true")
    Page<LibroEntity> obtenerListaActivos(Pageable pageable);

    @Query("SELECT l FROM LibroEntity l WHERE l.estatus = true AND l.idLibro = :idLibro")
    Optional<LibroEntity> obtenerLibroActivo(Integer idLibro);

}
