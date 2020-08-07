package br.dev.pedro.pdfinvoices.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

// ControllerAdvice write html to the response, RestController write xml, json or another format

@RestControllerAdvice // tell spring to apply what this class contains to all Controllers and RestControllers
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        return "Sorry, that was not quite right: " + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolantion(ConstraintViolationException exception) {
        return "Sorry, that was not quite right: " + exception.getMessage();
    }
}
