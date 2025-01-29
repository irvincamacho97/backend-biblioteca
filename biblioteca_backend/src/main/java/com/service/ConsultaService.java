package com.service;

import com.model.response.BaseResponse;
import com.model.response.ConsultaLibroResponse;
import com.model.response.PaginadoResponse;

public interface ConsultaService {

    abstract BaseResponse<PaginadoResponse<ConsultaLibroResponse>> obtenerListaEstatusConsulta(int page, int size, Integer idEstatusLibro);

    abstract BaseResponse<String> consultaAprobacion(Integer idConsulta, Integer idEstatusConsulta);

}
