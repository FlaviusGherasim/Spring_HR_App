package com.sda.springhrapp.exception;

public class DepartmentServiceException extends RuntimeException {
    public DepartmentServiceException(String errorMessage)
    {
        super(errorMessage);
    }
}