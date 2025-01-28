package com.controller;

import org.springframework.web.bind.annotation.RestController;

import com.model.response.BaseResponse;
import com.model.response.CatResponse;
import com.service.CatRolUsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class CatRolUsuarioController extends BaseController{

    @Autowired
    private CatRolUsuarioService catRolUsuarioService;

    @GetMapping(value="/catalogo/rol-usurio")
    public BaseResponse<List<CatResponse>> getCatRolUsuario() {
        return catRolUsuarioService.getCatRolUsuario();
    }
    

}
