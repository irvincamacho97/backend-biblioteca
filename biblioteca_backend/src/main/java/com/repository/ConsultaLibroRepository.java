package com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entity.ConsultaLibroEntity;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ConsultaLibroRepository extends JpaRepository<ConsultaLibroEntity,Integer> {

    @Query("SELECT c FROM ConsultaLibroEntity c WHERE c.estatusConsulta.idEstatusConsulta = :idEstatusLibro")
    Page<ConsultaLibroEntity> obtenerListaSolicitudes(Pageable pageable, Integer idEstatusLibro);
    
    @Modifying
    @Transactional
    @Query("UPDATE ConsultaLibroEntity c SET c.estatusConsulta.idEstatusConsulta = :idCatEstatus WHERE c.idConsulta = :idConsulta")
    void actualizarEstatusConsulta(
        @Param("idCatEstatus") Integer idCatEstatus,
        @Param("idConsulta") Integer idConsulta
    );

    @Query("SELECT c FROM ConsultaLibroEntity c WHERE c.estatusConsulta.idEstatusConsulta = 2 AND c.usuarioEntity.idUsuario = :idUsuario")
    Page<ConsultaLibroEntity> obtenerListaPrestadoToUsuaurio(Pageable pageable, Integer idUsuario);
    
}
