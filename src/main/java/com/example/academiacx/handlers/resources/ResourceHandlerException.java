package com.example.academiacx.handlers.resources;

import com.example.academiacx.handlers.exceptions.ErrorDetail;
import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceHandlerException {

    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<ErrorDetail> handlerParametroInvalidoException(InvalidParamException exception, HttpServletRequest request)
    {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorDetail(exception.getMessage(), 406L, 406L, System.currentTimeMillis(), "http://localhost:8080/erros/406"));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handlerRecursoNaoEntradoException(ResourceNotFoundException exception, HttpServletRequest request)
    {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ErrorDetail(exception.getMessage(), 406L, 406L, System.currentTimeMillis(), "http://localhost:8080/erros/406"));
    }

}