package com.jamal.dainvestment.controller;

import com.jamal.dainvestment.exception.ApplicationError;
import com.jamal.dainvestment.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This is a Javadoc comment
 * param <T> the parameter of the class
 */

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)

    /**
     * This is a Javadoc comment
     */
    public ResponseEntity<ApplicationError> handleCustomerNotFoundException(DataNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();

        error.setCode(404);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
