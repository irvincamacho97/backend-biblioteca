package com.util.mapper;

import com.entity.LibroEntity;
import com.model.request.LibroModificarRequest;
import com.model.request.LibroRegistroRequest;
import com.model.response.LibroResponse;
import com.util.enums.Estatus;

public class LibroMapper {

    public static LibroResponse mapperToLibroResponse(LibroEntity libro) {
		return 
        LibroResponse.builder()
        .idLibro(libro.getIdLibro())
        .tituloLibro(libro.getTituloLibro())
        .autorLibro(libro.getAutorLibro())
        .anioLibro(libro.getAnioLibro())
        .generoLibro(libro.getGeneroLibro())
        .build();
	}

  public static LibroEntity mapperToLibroEntity(LibroRegistroRequest request){

    final LibroEntity libroEntity = new LibroEntity();

		libroEntity.setTituloLibro(request.getTituloLibro());
		libroEntity.setAutorLibro(request.getAutorLibro());
    libroEntity.setAnioLibro(request.getAnioLibro());
    libroEntity.setGeneroLibro(request.getGeneroLibro());
		libroEntity.setEstatus(Estatus.ACTIVO.getEstatus());
		return libroEntity;

  }

  public static LibroEntity mapperToLibroEntityModificar(LibroModificarRequest request, LibroEntity entity){
		entity.setTituloLibro(request.getTituloLibro());
		entity.setAutorLibro(request.getAutorLibro());
		entity.setGeneroLibro(request.getGeneroLibro());
		entity.setAnioLibro(request.getAnioLibro());
		return entity;
	}

}
