package com.model.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ConsultaLibroResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer idConsulta;
    
    private Integer idUsuario;
    private String correoUsuario;
    private String nombreUsuario;
    private String apellidoPaternoUsuario;
    private String apellidoMaternoUsuario;

    private Integer idLibro;
    private String tituloLibro;
    private String autorLibro;
    private Integer anioLibro;
    private String generoLibro;

    private Integer idEstatusConsulta;
    private String estatusConsulta;


}
