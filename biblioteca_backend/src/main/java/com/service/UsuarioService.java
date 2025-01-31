package com.service;

import com.model.request.AutenticacionRequest;
import com.model.request.UsuarioModificarRequest;
import com.model.request.UsuarioRegistroRequest;
import com.model.response.AutenticacionResponse;
import com.model.response.BaseResponse;
import com.model.response.PaginadoResponse;
import com.model.response.UsuarioResponse;

public interface UsuarioService {

    abstract BaseResponse<PaginadoResponse<UsuarioResponse>> obtenerListaUsuario(int page, int size);

    abstract BaseResponse<UsuarioResponse> obtener(Integer idUsuario);

    abstract BaseResponse<String> registro(UsuarioRegistroRequest request);

    abstract BaseResponse<String> modificar(UsuarioModificarRequest request);

    abstract BaseResponse<String> eliminar(Integer idUsuario);

    abstract BaseResponse<AutenticacionResponse> autenticarUsuario( AutenticacionRequest request);


}
