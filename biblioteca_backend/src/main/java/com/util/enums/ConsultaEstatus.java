package com.util.enums;

import lombok.Getter;

public enum ConsultaEstatus {

    SOLICITADO(1),   
    APROBADO(2),
    DEVUELTO(3);

    @Getter
    private Integer idEstatus;

    ConsultaEstatus(Integer idEstatus) {
        this.idEstatus = idEstatus;
    }

}
