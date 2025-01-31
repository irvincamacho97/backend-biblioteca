package com.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;

@Getter
public class AutenticacionRequest {

    @JsonAlias("correo")
    private String correo;

    @JsonAlias("contrasenia")
    private String contrasenia;

}
