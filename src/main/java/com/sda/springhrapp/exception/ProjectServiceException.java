package com.sda.springhrapp.exception;

public class ProjectServiceException extends RuntimeException {
    public ProjectServiceException(String errorMessage)
    {
        super(errorMessage);
    }
}