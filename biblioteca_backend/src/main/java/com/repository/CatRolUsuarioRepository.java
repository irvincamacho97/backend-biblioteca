package com.repository;

import com.entity.CatRolUsuarioEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRolUsuarioRepository extends JpaRepository<CatRolUsuarioEntity, Integer>{

    @Query(value = "Select c FROM CatRolUsuarioEntity c ORDER BY c.rolUsuario ASC")
	List<CatRolUsuarioEntity> getCatRolUsuario();
}
