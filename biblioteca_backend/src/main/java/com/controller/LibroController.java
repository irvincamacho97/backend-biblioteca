package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.model.request.LibroModificarRequest;
import com.model.request.LibroRegistroRequest;
import com.model.response.BaseResponse;
import com.model.response.LibroResponse;
import com.model.response.PaginadoResponse;
import com.service.LibroService;

@RestController
public class LibroController extends BaseController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/libro")
    public BaseResponse<PaginadoResponse<LibroResponse>> obtenerListaLibro(
        @RequestParam("page") Integer page,
        @RequestParam("size") Integer size) {
        return libroService.obtenerListaLibro(page,size);
    }

    @GetMapping("/libro/{idLibro}")
    public BaseResponse<LibroResponse> obtener(@PathVariable Integer idLibro) {
        return libroService.obtener(idLibro);
    }

    @PostMapping("/libro")
    public BaseResponse<String> registro(@RequestBody LibroRegistroRequest request) {
        return libroService.registro(request);
    }

    @PutMapping("/libro")
    public BaseResponse<String> modificar(@RequestBody LibroModificarRequest request) {
        return libroService.modificar(request);
    }

    @DeleteMapping("/libro/{idLibro}")
    public BaseResponse<String> eliminar(@PathVariable Integer idLibro) {
        return libroService.eliminar(idLibro);
    }

}
