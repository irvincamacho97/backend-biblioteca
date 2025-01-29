package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.entity.CatEstatusConsultaEntity;
import com.entity.ConsultaLibroEntity;
import com.entity.LibroEntity;
import com.entity.UsuarioEntity;
import com.model.request.ConsultaLibroRequest;
import com.model.response.BaseResponse;
import com.model.response.ConsultaLibroResponse;
import com.model.response.LibroResponse;
import com.model.response.PaginadoResponse;
import com.repository.CatEstatusConsultaRepository;
import com.repository.ConsultaLibroRepository;
import com.repository.LibroRepository;
import com.repository.UsuarioRepository;
import com.service.ConsultaService;
import com.util.enums.ConsultaEstatus;
import com.util.enums.Estatus;
import com.util.mapper.PaginacionMapper;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaLibroRepository consultaLibroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository; 

    @Autowired
    private CatEstatusConsultaRepository catEstatusConsultaRepository;

    @Override
    public BaseResponse<String> consultaLibroRegistro(ConsultaLibroRequest request) {

        BaseResponse<String> response = new BaseResponse<>();

        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.obtenerUsuarioActivo(request.getIdUsuarioConsulta());

        Optional<LibroEntity> libroOpt = libroRepository.obtenerLibroActivo(request.getIdLibro());

        Optional<CatEstatusConsultaEntity> catEstatusOptional = catEstatusConsultaRepository.findById(ConsultaEstatus.SOLICITADO.getIdEstatus());

        ConsultaLibroEntity consultaLibroEntity = new ConsultaLibroEntity();

        if(usuarioOpt.isPresent()){
            consultaLibroEntity.setUsuarioEntity(usuarioOpt.get());
        }else{
            response.setMensaje("No se encontro información del usuario.");
            return response;
        }

        if(libroOpt.isPresent()){
            consultaLibroEntity.setLibroEntity(libroOpt.get());
        }else{
            response.setMensaje("No se encontro información del libro.");
            return response;
        }

        if(catEstatusOptional.isPresent()){
            consultaLibroEntity.setEstatusConsulta(catEstatusOptional.get());

        }else{
            response.setMensaje("No se encontro información del estatus.");
            return response;
        }

        consultaLibroRepository.save(consultaLibroEntity);

        response.setMensaje("Se realizo la solicitud con exito.");

        return response;

    }    

    @Override
    public BaseResponse<PaginadoResponse<ConsultaLibroResponse>> obtenerListaEstatusConsulta(int page, int size, Integer idEstatusLibro) {

        BaseResponse<PaginadoResponse<ConsultaLibroResponse>> response = new BaseResponse<>();
        
        PaginadoResponse<ConsultaLibroResponse> datos = new PaginadoResponse<>();

        Page<ConsultaLibroEntity> result = consultaLibroRepository.obtenerListaSolicitudes(PageRequest.of(page - 1, size),idEstatusLibro );

        List<ConsultaLibroResponse> elementos = new ArrayList<>();

    
        for (ConsultaLibroEntity consulta : result.getContent()) {
                    elementos.add(
                        ConsultaLibroResponse.builder()
                            .idConsulta(consulta.getIdConsulta())
                
                            .idUsuario(consulta.getUsuarioEntity().getIdUsuario())
                            .correoUsuario(consulta.getUsuarioEntity().getCorreo())
                            .nombreUsuario(consulta.getUsuarioEntity().getNombre())
                            .apellidoPaternoUsuario(consulta.getUsuarioEntity().getApellidoPaterno())
                            .apellidoMaternoUsuario(consulta.getUsuarioEntity().getApellidoMaterno())
                
                            .idLibro(consulta.getLibroEntity().getIdLibro())
                            .tituloLibro(consulta.getLibroEntity().getTituloLibro())
                            .autorLibro(consulta.getLibroEntity().getAutorLibro())
                            .anioLibro(consulta.getLibroEntity().getAnioLibro())
                            .generoLibro(consulta.getLibroEntity().getGeneroLibro())
                
                            .idEstatusConsulta(consulta.getEstatusConsulta().getIdEstatusConsulta())
                            .estatusConsulta(consulta.getEstatusConsulta().getEstatusConsulta())
                            .build()
                    );
                }
                
        datos.setElementos(elementos);
        datos.setPaginacion(PaginacionMapper.mapper(result));
        response.setMensaje("Consulta de manera exitosa.");
        response.setDatos(datos);

        return response;
    }

    @Override
    public BaseResponse<String> consultaAprobacion(Integer idConsulta,Integer idEstatusConsulta) {
    
        BaseResponse<String> response = new BaseResponse<>();
    
        Optional<ConsultaLibroEntity> optConsulta = consultaLibroRepository.findById(idConsulta);
    
        if (optConsulta.isPresent()) {
            
            consultaLibroRepository.actualizarEstatusConsulta(idEstatusConsulta, idConsulta);

            response.setMensaje("Se actualizo la consulta del libro");
        } else {
            response.setMensaje("No existe la consulta del libro");
        }
    
        return response;
    }

    @Override
    public BaseResponse<PaginadoResponse<ConsultaLibroResponse>> obtenerListaPrestadoToUsuaurio(int page, int size,
            Integer idUsuario) {

                BaseResponse<PaginadoResponse<ConsultaLibroResponse>> response = new BaseResponse<>();
        
                PaginadoResponse<ConsultaLibroResponse> datos = new PaginadoResponse<>();
        
                Page<ConsultaLibroEntity> result = consultaLibroRepository.obtenerListaPrestadoToUsuaurio(PageRequest.of(page - 1, size),idUsuario);
        
                List<ConsultaLibroResponse> elementos = new ArrayList<>();
        
            
                for (ConsultaLibroEntity consulta : result.getContent()) {
                            elementos.add(
                                ConsultaLibroResponse.builder()
                                    .idConsulta(consulta.getIdConsulta())
                        
                                    .idUsuario(consulta.getUsuarioEntity().getIdUsuario())
                                    .correoUsuario(consulta.getUsuarioEntity().getCorreo())
                                    .nombreUsuario(consulta.getUsuarioEntity().getNombre())
                                    .apellidoPaternoUsuario(consulta.getUsuarioEntity().getApellidoPaterno())
                                    .apellidoMaternoUsuario(consulta.getUsuarioEntity().getApellidoMaterno())
                        
                                    .idLibro(consulta.getLibroEntity().getIdLibro())
                                    .tituloLibro(consulta.getLibroEntity().getTituloLibro())
                                    .autorLibro(consulta.getLibroEntity().getAutorLibro())
                                    .anioLibro(consulta.getLibroEntity().getAnioLibro())
                                    .generoLibro(consulta.getLibroEntity().getGeneroLibro())
                        
                                    .idEstatusConsulta(consulta.getEstatusConsulta().getIdEstatusConsulta())
                                    .estatusConsulta(consulta.getEstatusConsulta().getEstatusConsulta())
                                    .build()
                            );
                        }
                        
                datos.setElementos(elementos);
                datos.setPaginacion(PaginacionMapper.mapper(result));
                response.setMensaje("Consulta de manera exitosa.");
                response.setDatos(datos);
        
                return response;
    }

}
