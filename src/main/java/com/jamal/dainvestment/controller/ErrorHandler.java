package com.jamal.dainvestment.controller;

import com.jamal.dainvestment.exception.ApplicationError;
import com.jamal.dainvestment.exception.DataNotFoundException;
import com.jamal.dainvestment.exception.NullableFalseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /**
     * Exception handler Data not Found
     * This is a Javadoc comment
     * @param exception the parameter of the class
     * @param webRequest the parameter of the class
     * @return exception message
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApplicationError> dataNotFoundException(DataNotFoundException exception, WebRequest webRequest){
        ApplicationError error = new ApplicationError();

        error.setCode(404);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Method argument not valid exception handler response entity.
     *
     * @param exception the ex
     * @param webRequest the web request
     * @return the response entity
     */
    @ResponseBody
    @ExceptionHandler(NullableFalseException.class)
    public ResponseEntity<ApplicationError> nullableFalseException(NullableFalseException exception, WebRequest webRequest) {
        ApplicationError error = new ApplicationError();

        error.setCode(500);
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
