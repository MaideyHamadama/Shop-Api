package com.shop.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(org.springframework.dao.DataIntegrityViolationException ex) {
        if (ex.getMessage().contains("email")) {
            return new ResponseEntity<>("L'email est déjà utilisé.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Une erreur est survenue. Veuillez réessayer.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Erreur inattendue : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

