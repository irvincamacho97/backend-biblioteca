package com.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public class AutenticacionResponse {
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

    @JsonProperty("token")
	private String token;
}
