package com.util.mapper;

import com.entity.UsuarioEntity;
import com.model.request.UsuarioModificarRequest;
import com.model.request.UsuarioRegistroRequest;
import com.model.response.UsuarioResponse;
import com.util.enums.Estatus;


public class UsuarioMapper {

    	public static UsuarioEntity mapperToUsuarioEntity(UsuarioRegistroRequest request){

		final UsuarioEntity usuarioEntity = new UsuarioEntity();

		usuarioEntity.setNombre(request.getNombre());
		usuarioEntity.setApellidoPaterno(request.getApellidoPaterno());
		usuarioEntity.setApellidoMaterno(request.getApellidoMaterno());
		usuarioEntity.setContrasena(request.getContrasena());
		usuarioEntity.setCorreo(request.getCorreo());
		usuarioEntity.setEstatus(Estatus.ACTIVO.getEstatus());
		return usuarioEntity;
	}


    public static UsuarioEntity mapperToUsuarioEntityModificar(UsuarioModificarRequest request, UsuarioEntity entity){

		entity.setNombre(request.getNombre());
		entity.setApellidoPaterno(request.getApellidoPaterno());
		entity.setApellidoMaterno(request.getApellidoMaterno());
		entity.setContrasena(request.getContrasena());
		entity.setCorreo(request.getCorreo());

		return entity;
	}

	public static UsuarioResponse mapperToUsuarioResponse(UsuarioEntity entity) {
		return UsuarioResponse.builder()
				.idUsuario(entity.getIdUsuario())
				.nombre(entity.getNombre())
				.apellidoPaterno(entity.getApellidoPaterno())
				.apellidoMaterno(entity.getApellidoMaterno())
				.correo(entity.getCorreo())
				.build();
	}
	


}
