package com.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRegistroRequest {

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
    
    @JsonAlias("id_rol_usuario")
    private Integer idRolUsuario;

    @Override
	public String toString() {
        final StringBuilder sb = new StringBuilder();
		sb.append("UsuarioRegistroRequest:[")
			.append("nombre=").append(this.nombre).append(",")
			.append("apellidoPaterno=").append(this.apellidoPaterno).append(",")
			.append("apellidoMaterno=").append(this.apellidoMaterno).append(",")
            .append("idRolUsuario=").append(this.idRolUsuario)
			.append("]");
		return sb.toString();
	} 

}
