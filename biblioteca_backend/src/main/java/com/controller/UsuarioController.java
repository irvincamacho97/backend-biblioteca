package com.controller;

import org.springframework.web.bind.annotation.RestController;

import com.model.request.AutenticacionRequest;
import com.model.request.UsuarioModificarRequest;
import com.model.request.UsuarioRegistroRequest;
import com.model.response.AutenticacionResponse;
import com.model.response.BaseResponse;
import com.model.response.PaginadoResponse;
import com.model.response.UsuarioResponse;
import com.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UsuarioController extends BaseController{


    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public BaseResponse<PaginadoResponse<UsuarioResponse>> obtenerListaUsuario(
        @RequestParam("page") Integer page,
        @RequestParam("size") Integer size) {
        return usuarioService.obtenerListaUsuario(page,size);
    }

    @GetMapping("/usuario/{idUsuario}")
    public BaseResponse<UsuarioResponse> obtener(@PathVariable Integer idUsuario) {
        return usuarioService.obtener(idUsuario);
    }
    
    

    @PostMapping("/usuario")
    public BaseResponse<String> registro(@RequestBody UsuarioRegistroRequest request) {
        return usuarioService.registro(request);
    }

    @PutMapping("/usuario")
    public BaseResponse<String> modificar(@RequestBody UsuarioModificarRequest request) {
        return usuarioService.modificar(request);
    }

    @DeleteMapping("/usuario/{idUsuario}")
    public BaseResponse<String> eliminar(@PathVariable Integer idUsuario) {
        return usuarioService.eliminar(idUsuario);
    }

    @PostMapping("usuario/autenticar")
    public BaseResponse<AutenticacionResponse> autenticarUsuario(@RequestBody AutenticacionRequest request) {
        return usuarioService.autenticarUsuario(request);
    }
    
    

}
