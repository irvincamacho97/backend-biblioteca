package com.util.enums;

import lombok.Getter;

public enum EstatusUsuarioEnum {

    ACTIVO(true),   
    ELIMINADO(false);

    @Getter
    private Boolean estatus;

    EstatusUsuarioEnum(Boolean estatus) {
        this.estatus = estatus;
    }

}
