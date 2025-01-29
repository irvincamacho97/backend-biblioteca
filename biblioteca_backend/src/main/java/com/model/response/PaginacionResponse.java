package com.model.response;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginacionResponse implements Serializable{

    private static final long serialVersionUID = 1L;
	
	/** Número de la pagina solicitada **/
	@JsonProperty("num_pag")
	private int numPag = 0;
	
	/** Número de elementos por pagina **/
	@JsonProperty("tam_pag")
    private int tamPag = 0;
    
	/** Total de elementos que existen **/
	@JsonProperty("total_elementos")
    private long totalElements = 0;
    
	/** Total de paginas **/
	@JsonProperty("total_paginas")
    private int totalPaginas = 0;
    
	/** Identifica si se esta consultando la ultima pagina **/
	@JsonProperty("es_ultima_pag")
    private boolean esUltimaPag = true;
	
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PaginacionResponse:[")
            .append("num_pag=").append(this.numPag).append(", ")
            .append("tam_pag=").append(this.tamPag).append(", ")
            .append("total_elementos=").append(this.totalElements).append(", ")
            .append("total_paginas=").append(this.totalPaginas).append(", ")
            .append("es_ultima_pag=").append(this.esUltimaPag)
        .append("]");
        return sb.toString();
    }
}
