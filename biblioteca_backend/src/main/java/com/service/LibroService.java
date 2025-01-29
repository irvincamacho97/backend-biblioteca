package com.service;

import com.model.request.LibroModificarRequest;
import com.model.request.LibroRegistroRequest;
import com.model.response.BaseResponse;
import com.model.response.LibroResponse;
import com.model.response.PaginadoResponse;

public interface LibroService {

    abstract BaseResponse<PaginadoResponse<LibroResponse>> obtenerListaLibro(int page, int size);

    abstract BaseResponse<LibroResponse> obtener(Integer idLibro);

    abstract BaseResponse<String> registro(LibroRegistroRequest request);

    abstract BaseResponse<String> modificar(LibroModificarRequest request);

    abstract BaseResponse<String> eliminar(Integer idLibro);

}
