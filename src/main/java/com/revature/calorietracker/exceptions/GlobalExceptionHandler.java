package com.revature.calorietracker.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleItemNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        //ToDo: decipher exception to provide client more specific information about their request failure
//        String resMsg;
//        if(ex.getMessage().contains("ERROR: duplicate key value violates unique constraint \"users_email_key\"")) resMsg = "Email address already registered.";
//        else resMsg=ex.getMessage();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        //ToDo: decipher exception to provide client more specific information about their request failure
        String resMsg;
        if (ex.getMessage().contains("ERROR: duplicate key value violates unique constraint \"users_email_key\""))
            resMsg = "Email address already exists.";
        else if (ex.getMessage().contains("username") && ex.getMessage().contains("already exists"))
            resMsg = "Username already exists.";
        else resMsg = ex.getMessage();
        return new ResponseEntity<>(resMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BMIRecordNotFoundException.class)
    public ResponseEntity<String> handleBMIRecordNotFoundException(BMIRecordNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
