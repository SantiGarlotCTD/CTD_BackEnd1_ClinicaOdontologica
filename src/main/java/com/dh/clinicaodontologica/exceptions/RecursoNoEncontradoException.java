package com.dh.clinicaodontologica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends Exception{

    public RecursoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
