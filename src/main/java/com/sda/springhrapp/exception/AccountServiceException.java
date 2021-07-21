package com.sda.springhrapp.exception;

public class AccountServiceException extends  Exception {
    public AccountServiceException(String errorMessage)
    {
        super(errorMessage);
    }
}
