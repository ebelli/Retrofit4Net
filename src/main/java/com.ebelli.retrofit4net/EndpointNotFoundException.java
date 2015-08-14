package com.ebelli.retrofit4net;

/**
 * Created by ebelli on 08/07/15.
 */
public class EndpointNotFoundException extends Exception{
    public EndpointNotFoundException() {
    }

    public EndpointNotFoundException(String message) {
        super(message);
    }

    public EndpointNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EndpointNotFoundException(Throwable cause) {
        super(cause);
    }

    public EndpointNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
