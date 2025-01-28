package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CatRolUsuarioEntity;
import com.model.response.BaseResponse;
import com.model.response.CatResponse;
import com.repository.CatRolUsuarioRepository;
import com.service.CatRolUsuarioService;

@Service
public class CatRolUsuarioServiceImpl implements CatRolUsuarioService{

    @Autowired
    private CatRolUsuarioRepository catRolUsuarioRepository;

    @Override
    public BaseResponse<List<CatResponse>> getCatRolUsuario() {

        BaseResponse<List<CatResponse>> resultado = new BaseResponse<>();

        List<CatResponse> listCatalogos = new ArrayList<>();

        List<CatRolUsuarioEntity> catalogos = catRolUsuarioRepository.getCatRolUsuario();

        for (CatRolUsuarioEntity catalogo : catalogos) {
            
            listCatalogos.add(
                CatResponse.builder()
                .id(catalogo.getIdRolUsuario())
                .item(catalogo.getRolUsuario())
            .build());
        }

        resultado.setMensaje("Consulta de catálogo de estatus de rol usuario fue exitosa.");
        resultado.setDatos(listCatalogos);

        return resultado;
    }

}
