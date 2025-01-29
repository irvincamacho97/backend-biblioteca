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

@Table(name = "libro")
@Entity
@NoArgsConstructor
@Setter
@Getter
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private Integer idLibro;
    
    @Column(name = "titulo_libro")
    private String tituloLibro;

    @Column(name = "autor_libro")
    private String autorLibro;

    @Column(name = "anio_libro")
    private Integer anioLibro;

    @Column(name = "genero_libro")
    private String generoLibro;

    @Column(name = "estatus")
    private Boolean estatus;


}
