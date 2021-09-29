package com.dh.clinicaodontologica.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({RecursoNoEncontradoException.class})
    public ResponseEntity<String> recursoNoEncontrado(RecursoNoEncontradoException ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status( HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<?> todosLosErrores(Exception ex){
        logger.error(ex.getMessage());
        return new ResponseEntity("Error: "+ ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
