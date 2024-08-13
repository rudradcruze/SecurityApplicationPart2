package org.rudradcruze.securityapp.securityapplicationpart2.advice;

import io.jsonwebtoken.JwtException;
import org.rudradcruze.securityapp.securityapplicationpart2.exceptions.IllegalArgumentException;
import org.rudradcruze.securityapp.securityapplicationpart2.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException exception) {
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage());
//        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Input validation failed");
        apiError.setSubErrors(exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage()).toList());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiError> handleJwtException(JwtException exception) {
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

}
