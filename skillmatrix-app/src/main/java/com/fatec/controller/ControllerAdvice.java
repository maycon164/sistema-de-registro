package com.fatec.controller;


import com.fatec.dto.ErrorDTO;
import com.fatec.dto.FieldErrorDTO;
import com.fatec.exceptions.NotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);
        var errors = methodArgumentNotValidException.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(FieldErrorDTO::new).toList());
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorDTO>  handleNotFoundException(NotFound ex) {
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
