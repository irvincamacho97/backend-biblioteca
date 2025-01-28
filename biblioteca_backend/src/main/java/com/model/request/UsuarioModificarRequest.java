package com.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModificarRequest {

    @JsonAlias("id_usuario")
    private Integer idUsuario;

    @JsonAlias("correo")
    private String correo;
    
    @JsonAlias("contrasena")
    private String contrasena;

    @JsonAlias("nombre")
    private String nombre;

    @JsonAlias("apellido_paterno")
    private String apellidoPaterno;
    
    @JsonAlias("apellido_materno")
    private String apellidoMaterno;

}
