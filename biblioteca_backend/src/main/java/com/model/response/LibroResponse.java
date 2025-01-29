package com.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LibroResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    @JsonProperty("id_libro")
    private Integer idLibro;

    @JsonProperty("titulo_libro")
    private String tituloLibro;

    @JsonProperty("autor_libro")
    private String autorLibro;

    @JsonProperty("anio_libro")
    private Integer anioLibro;

    @JsonProperty("genero_libro")
    private String generoLibro;

}
