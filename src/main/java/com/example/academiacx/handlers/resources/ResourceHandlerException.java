package com.example.academiacx.handlers.resources;

import com.example.academiacx.handlers.exceptions.ErrorDetail;
import com.example.academiacx.handlers.exceptions.InvalidParamException;
import com.example.academiacx.handlers.exceptions.ResourceNotFoundException;
import com.example.academiacx.handlers.exceptions.SaveFavoritesException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceHandlerException extends Exception {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status,
                                                               WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });
        ErrorDetail errorDetail = new ErrorDetail(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
    }

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

    @ExceptionHandler(SaveFavoritesException.class)
    public ResponseEntity<ErrorDetail> handlerSaveFavoritesException(SaveFavoritesException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDetail(exception.getMessage(), 500L, 500L, System.currentTimeMillis(), "http://localhost:8080/errors/500"));
    }


}