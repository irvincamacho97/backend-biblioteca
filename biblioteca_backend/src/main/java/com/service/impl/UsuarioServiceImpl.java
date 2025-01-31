package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import com.entity.CatRolUsuarioEntity;
import com.entity.UsuarioEntity;
import com.model.request.AutenticacionRequest;
import com.model.request.UsuarioModificarRequest;
import com.model.request.UsuarioRegistroRequest;
import com.model.response.AutenticacionResponse;
import com.model.response.BaseResponse;
import com.model.response.PaginadoResponse;
import com.model.response.UsuarioResponse;
import com.repository.CatRolUsuarioRepository;
import com.repository.UsuarioRepository;
import com.security.JwtUtil;
import com.service.UsuarioService;
import com.util.enums.Estatus;
import com.util.mapper.PaginacionMapper;
import com.util.mapper.UsuarioMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private CatRolUsuarioRepository catRolUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public BaseResponse<PaginadoResponse<UsuarioResponse>> obtenerListaUsuario(int page, int size) {
        log.info("obtenerListaUsuario:{}");

        BaseResponse<PaginadoResponse<UsuarioResponse>> response = new BaseResponse<>();

        PaginadoResponse<UsuarioResponse> datos = new PaginadoResponse<>();

        Page<UsuarioEntity> result = usuarioRepository.obtenerListaActivos(PageRequest.of(page - 1, size));

        List<UsuarioResponse> elementos = new ArrayList<>();
        for (UsuarioEntity usuario : result.getContent()) {
            elementos.add(
                    UsuarioResponse.builder()
                            .idUsuario(usuario.getIdUsuario())
                            .correo(usuario.getCorreo())
                            .nombre(usuario.getNombre())
                            .apellidoMaterno(usuario.getApellidoMaterno())
                            .apellidoPaterno(usuario.getApellidoPaterno())
                            .idRolUsuario(usuario.getCatRolUsuarioEntity().getIdRolUsuario())
                            .rolUsuario(usuario.getCatRolUsuarioEntity().getRolUsuario())
                            .build());
        }

        datos.setElementos(elementos);
        datos.setPaginacion(PaginacionMapper.mapper(result));
        response.setMensaje("Consulta de manera exitosa.");
        response.setDatos(datos);
        log.info("busqueda response:{}", response);
        return response;
    }

    
    @Override
    public BaseResponse<UsuarioResponse> obtener(Integer idUsuario) {

        log.info("obtener request:{}",idUsuario);

        BaseResponse<UsuarioResponse> response = new BaseResponse<>();

        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.obtenerUsuarioActivo(idUsuario);

        if(usuarioOpt.isPresent()) {

            response.setDatos(UsuarioMapper.mapperToUsuarioResponse(usuarioOpt.get()));  
            response.setMensaje("Busqueda exitosa."); 
    
            }else{
                
                response.setMensaje("No se encontro información del usuario.");
            }
    
            return response;
    }


    @Override
    public BaseResponse<String> registro(UsuarioRegistroRequest request) {

        log.info("Registrar Usuario:{}", request);

        /** Crea una instancia de BaseResponse para la respuesta **/
        BaseResponse<String> response = new BaseResponse<>();

        Optional<CatRolUsuarioEntity> catRolUsuarioOptional = catRolUsuarioRepository.findById(request.getIdRolUsuario());

        /** Valida la existencia del id del catálogo rol usuario **/
        if( catRolUsuarioOptional.isEmpty()) {
            response.setMensaje("No exite registro del rol con id "+request.getIdRolUsuario());
            return response;
        }

        CatRolUsuarioEntity CatRolUsuarioEntity =  catRolUsuarioOptional.get();

        /** Valida si existe un registro con el mismo correo **/
        if (usuarioRepository.existsByCorreo(request.getCorreo()) > 0) {
            response.setMensaje("Ya exite un registro con: " + request.getCorreo());
            return response;
        }

        UsuarioEntity usuarioEntity = UsuarioMapper.mapperToUsuarioEntity(request);

        usuarioEntity.setCatRolUsuarioEntity(CatRolUsuarioEntity);

        /** Guarda la enitdad en la base de datos **/
        usuarioRepository.save(usuarioEntity); 

        response.setMensaje("Usuario registrador exitosamente");
        response.setDatos(usuarioEntity.getCorreo());

        return response;
    }

    @Override
    public BaseResponse<String> modificar(UsuarioModificarRequest request) {

        log.info("Modificar Usuario:{}", request);

        /** Crea una instancia de BaseResponse para la respuesta **/
        BaseResponse<String> response = new BaseResponse<>();

        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(request.getIdUsuario());

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        if (optionalUsuario.isPresent()) {

            usuarioEntity = UsuarioMapper.mapperToUsuarioEntityModificar(request, optionalUsuario.get()) ;

            usuarioRepository.save(usuarioEntity);
        }else{
            response.setMensaje("No existe el usuario");
            return response;
        }

        response.setMensaje("Modificación exitosa del usuario");
        return response;

    }

    @Override
    public BaseResponse<String> eliminar(Integer idUsuario) {

        BaseResponse<String> response = new BaseResponse<>();

        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(idUsuario);

        if (optionalUsuario.isPresent()) {

            optionalUsuario.get().setEstatus(Estatus.ELIMINADO.getEstatus());

            usuarioRepository.save(optionalUsuario.get());
        }else {
            response.setMensaje("No existe el usuario");
            return response;
        }
            response.setMensaje("Se elimino el usuario correctamente");
        return response;
    }


    @Override
    public BaseResponse<AutenticacionResponse> autenticarUsuario(AutenticacionRequest request) {


        log.info("autenticar:{}", request);

        BaseResponse<AutenticacionResponse> response = new BaseResponse<>();

        Optional<UsuarioEntity> optionalUsuario = usuarioRepository.getInfoUsuario(request.getCorreo());

        if (!optionalUsuario.isPresent()) {
            response.setMensaje("Credenciales inválidas.");
            return response;
        }

        UsuarioEntity usuario = optionalUsuario.get();

        if (!usuario.getContrasena().equals(request.getContrasenia())) {
            response.setMensaje("Credenciales inválidas.");
            return response;
        } 

        String token = jwtUtil.generateToken(usuario.getIdUsuario(),usuario.getCorreo());

        
        response.setMensaje("Autenticación exitosa.");
        response.setDatos(UsuarioMapper.mapperToAutenticarResponse(usuario, token));
        return response;

    }
    

}
