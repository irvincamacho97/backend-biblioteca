package com.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroModificarRequest {

    @JsonAlias("id_libro")
    private Integer idLibro;

    @JsonAlias("titulo_libro")
    private String tituloLibro;

    @JsonAlias("autor_libro")
    private String autorLibro;

    @JsonAlias("genero_libro")
    private String generoLibro;

    @JsonAlias("anio_libro")
    private Integer anioLibro;

}
