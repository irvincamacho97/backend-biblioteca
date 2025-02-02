package com.controller;

import org.springframework.web.bind.annotation.RestController;

import com.model.request.ConsultaLibroRequest;
import com.model.response.BaseResponse;
import com.model.response.ConsultaLibroResponse;
import com.model.response.PaginadoResponse;
import com.service.ConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;





@RestController
public class ConsultaController extends BaseController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/consulta-libro")
    public BaseResponse<String> consultaLibro(@RequestBody ConsultaLibroRequest request) {
        return consultaService.consultaLibroRegistro(request);
    }
    

    @GetMapping("/consulta-libro/{idEstatusLibro}")
    public  BaseResponse<PaginadoResponse<ConsultaLibroResponse>> obtenerListaEstatusConsulta(  
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @PathVariable Integer idEstatusLibro ) {
        return consultaService.obtenerListaEstatusConsulta(page,size,idEstatusLibro);
    }
    
    @PutMapping("/consulta-estatus")
    public BaseResponse<String> consultaAprobacion(
        @RequestParam("id_consulta") Integer idConsulta,
        @RequestParam("id_estatus_consulta") Integer idEstatusConsulta) {
        return consultaService.consultaAprobacion(idConsulta,idEstatusConsulta);
    }

    @GetMapping("/consulta-lista-prestado/{idUsuario}")
    public  BaseResponse<PaginadoResponse<ConsultaLibroResponse>> obtenerListaPrestadoToUsuaurio(  
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @PathVariable Integer idUsuario) {
        return consultaService.obtenerListaPrestadoToUsuaurio(page,size,idUsuario);
    }


}
