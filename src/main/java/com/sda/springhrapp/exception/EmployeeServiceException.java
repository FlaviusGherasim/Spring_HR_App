package com.sda.springhrapp.exception;

public class EmployeeServiceException extends  RuntimeException {
    public EmployeeServiceException(String errorMessage)
    {
        super(errorMessage);
    }
}