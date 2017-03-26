package com.cicidi.home.exception;

/**
 * Created by cicidi on 3/26/2017.
 */
public class ServiceException extends AbstractException {
    public ServiceException(String errorcode, String message) {
        super(errorcode, message);
    }
}
