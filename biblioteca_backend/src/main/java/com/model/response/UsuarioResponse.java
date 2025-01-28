package com.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsuarioResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id_usuario")
	private int idUsuario;

    @JsonProperty("correo")
	private String correo;

    @JsonProperty("nombre")
	private String nombre;

    @JsonProperty("apellido_materno")
	private String apellidoMaterno;

    @JsonProperty("apellido_paterno")
	private String apellidoPaterno;

    @JsonProperty("id_rol_usuario")
	private Integer idRolUsuario;

    @JsonProperty("rol_usuario")
	private String rolUsuario;

}
