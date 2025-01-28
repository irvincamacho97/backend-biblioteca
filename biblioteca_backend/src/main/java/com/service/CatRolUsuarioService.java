package com.service;

import java.util.List;

import com.model.response.BaseResponse;
import com.model.response.CatResponse;

public interface CatRolUsuarioService {

    abstract BaseResponse<List<CatResponse>> getCatRolUsuario();

}
