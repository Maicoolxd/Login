package org.maicol.login.services;

public class ServiceJdbcException extends RuntimeException {
    public ServiceJdbcException(String message){
        super(message);
    }
    public ServiceJdbcException (String message, Throwable cause) {
        super(message, cause);
    }
}
