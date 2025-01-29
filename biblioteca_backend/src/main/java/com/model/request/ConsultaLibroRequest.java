package com.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaLibroRequest {

    @JsonAlias("id_usuario_consulta")
    private Integer idUsuarioConsulta;

    @JsonAlias("id_libro")
    private Integer idLibro;

}
