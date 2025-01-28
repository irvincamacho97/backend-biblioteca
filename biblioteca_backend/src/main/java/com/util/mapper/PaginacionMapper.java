package com.util.mapper;

import com.model.response.PaginacionResponse;
import org.springframework.data.domain.Page;

public class PaginacionMapper {

    public static <T> PaginacionResponse mapper(Page<T> resultado) {
        PaginacionResponse response = new PaginacionResponse();
        response.setNumPag((resultado.isEmpty()) ? 0 : resultado.getNumber() + 1);
        response.setTamPag(resultado.getSize());
        response.setTotalElements(resultado.getTotalElements());
        response.setTotalPaginas(resultado.getTotalPages());
        response.setEsUltimaPag(resultado.isLast());
        return response;
    }
    

}
