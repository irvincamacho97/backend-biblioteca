package com.util.enums;

import lombok.Getter;

public enum Estatus {

    ACTIVO(true),   
    ELIMINADO(false);

    @Getter
    private Boolean estatus;

    Estatus(Boolean estatus) {
        this.estatus = estatus;
    }

}
