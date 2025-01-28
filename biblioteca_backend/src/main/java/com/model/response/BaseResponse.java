package com.model.response;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    private String mensaje;

    private T datos;

}
