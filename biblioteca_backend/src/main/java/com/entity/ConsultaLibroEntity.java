package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "consulta_libro")
@Entity
@NoArgsConstructor
@Setter
@Getter
public class ConsultaLibroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Integer idConsulta;

    @ManyToOne
    @JoinColumn(name = "id_usuario_consulta", nullable = false)
    private UsuarioEntity usuarioEntity;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private LibroEntity libroEntity;

    @ManyToOne
    @JoinColumn(name = "id_estatus_consulta", nullable = false)
    private CatEstatusConsultaEntity estatusConsulta;
}
