package com.sda.springhrapp.controller;

import com.sda.springhrapp.exception.AccountServiceException;
import com.sda.springhrapp.exception.DepartmentServiceException;
import com.sda.springhrapp.exception.EmployeeServiceException;
import com.sda.springhrapp.exception.ProjectServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>("Illegal arguments " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountServiceException.class)
    public ResponseEntity<String> catchAccountServiceException(AccountServiceException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DepartmentServiceException.class)
    public ResponseEntity<String> catchDepartmentServiceException(DepartmentServiceException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeServiceException.class)
    public ResponseEntity<String> catchEmployeeServiceException(EmployeeServiceException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProjectServiceException.class)
    public ResponseEntity<String> catchProjectServiceException(ProjectServiceException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
