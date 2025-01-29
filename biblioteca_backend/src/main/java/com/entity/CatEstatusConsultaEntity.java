package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cat_estatus_consulta")
@Entity
@NoArgsConstructor
@Setter
@Getter
public class CatEstatusConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estatus_consulta")
    private Integer idEstatusConsulta;

    @Column(name = "estatus_consulta", length = 50, nullable = false)
    private String estatusConsulta;
    
}
