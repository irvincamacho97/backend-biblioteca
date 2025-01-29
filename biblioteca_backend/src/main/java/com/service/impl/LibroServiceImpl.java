package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.entity.LibroEntity;
import com.model.request.LibroModificarRequest;
import com.model.request.LibroRegistroRequest;
import com.model.response.BaseResponse;
import com.model.response.LibroResponse;
import com.model.response.PaginadoResponse;
import com.repository.LibroRepository;
import com.service.LibroService;
import com.util.enums.Estatus;
import com.util.mapper.LibroMapper;
import com.util.mapper.PaginacionMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LibroServiceImpl implements LibroService{

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public BaseResponse<PaginadoResponse<LibroResponse>> obtenerListaLibro(int page, int size) {
        log.info("obtenerListaLibro:{}",page,size);

        BaseResponse<PaginadoResponse<LibroResponse>> response = new BaseResponse<>();

        PaginadoResponse<LibroResponse> datos = new PaginadoResponse<>();

        Page<LibroEntity> result = libroRepository.obtenerListaActivos(PageRequest.of(page - 1, size));

        List<LibroResponse> elementos = new ArrayList<>();
        
        for (LibroEntity libro : result.getContent()) {
            elementos.add(
                    LibroResponse.builder()
                            .idLibro(libro.getIdLibro())
                            .tituloLibro(libro.getTituloLibro())
                            .autorLibro(libro.getAutorLibro())
                            .anioLibro(libro.getAnioLibro())
                            .generoLibro(libro.getGeneroLibro())
                            .build());
        }

        datos.setElementos(elementos);
        datos.setPaginacion(PaginacionMapper.mapper(result));
        response.setMensaje("Consulta de manera exitosa.");
        response.setDatos(datos);
        log.info("obtenerListaLibro:{}", response);
        return response;


    }

    @Override
    public BaseResponse<LibroResponse> obtener(Integer idLibro) {
        
        log.info("obtenerLibro request:{}",idLibro);

        BaseResponse<LibroResponse> response = new BaseResponse<>();

        Optional<LibroEntity> libroOpt = libroRepository.obtenerLibroActivo(idLibro);

        if(libroOpt.isPresent()) {

            response.setDatos(LibroMapper.mapperToLibroResponse(libroOpt.get()));  
            response.setMensaje("Busqueda exitosa."); 
    
            }else{
                
                response.setMensaje("No se encontro información del libro.");
            }
    
        log.info("obtenerLibro response:{}",response);
            return response;
    }

    @Override
    public BaseResponse<String> registro(LibroRegistroRequest request) {

        log.info("Registrar Libro:{}", request);

        BaseResponse<String> response = new BaseResponse<>();

        LibroEntity libroEntity = LibroMapper.mapperToLibroEntity(request);

        libroRepository.save(libroEntity); 

        response.setMensaje("Usuario registrador exitosamente");
        response.setDatos(libroEntity.getTituloLibro());

        log.info("Registrar Libro:{}", response);

        return response;

    }

    @Override
    public BaseResponse<String> modificar(LibroModificarRequest request) {

        log.info("Modificar Libro:{}", request);
        
        BaseResponse<String> response = new BaseResponse<>();

        Optional<LibroEntity> optionalUsuario = libroRepository.findById(request.getIdLibro());

        LibroEntity libroEntity = new LibroEntity();

        if (optionalUsuario.isPresent()) {

            libroEntity = LibroMapper.mapperToLibroEntityModificar(request, optionalUsuario.get()) ;

            libroRepository.save(libroEntity);
        }else{
            response.setMensaje("No existe el libro");
            return response;
        }

        response.setMensaje("Modificación exitosa del libro");
        
        log.info("Modificar Libro:{}", response);
        return response;
    }

    @Override
    public BaseResponse<String> eliminar(Integer idLibro) {

        BaseResponse<String> response = new BaseResponse<>();

        Optional<LibroEntity> optionalLibro = libroRepository.findById(idLibro);

        if (optionalLibro.isPresent()) {

            optionalLibro.get().setEstatus(Estatus.ELIMINADO.getEstatus());

            libroRepository.save(optionalLibro.get());
        }else {
            response.setMensaje("No existe el libro");
            return response;
        }
            response.setMensaje("Se elimino el libro correctamente");
        return response;
    }    
}
