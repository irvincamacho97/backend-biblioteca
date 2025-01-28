package com.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginadoResponse <T extends Serializable> implements Serializable{

    private static final long serialVersionUID = 1L;

    
	@JsonProperty("elementos")
    protected List<T> elementos = new ArrayList<>();
	
	@JsonProperty("paginacion")
	protected PaginacionResponse paginacion;
	
	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PaginadoResponse:[")
            .append("elementos=").append(this.elementos).append(", ")
            .append("paginacion=").append(this.paginacion)
        .append("]");
        return sb.toString();
    }

}
