package br.dev.pedro.mybank.web;

import br.dev.pedro.mybank.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> fields = new ArrayList<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            fields.add(fieldError.getField());
        }

        return new ErrorDTO(exception.getMessage(), fields);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDTO handleConstraintViolantion(ConstraintViolationException exception) {
        List<String> fields = new ArrayList<>();

        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            fields.add(constraintViolation.getPropertyPath().toString());
        }

        return new ErrorDTO(exception.getMessage(), fields);
    }
}
