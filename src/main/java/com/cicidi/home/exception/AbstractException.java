package com.cicidi.home.exception;

/**
 * Created by cicidi on 3/26/2017.
 */
public abstract class AbstractException extends RuntimeException {
    public String errorcode;
    public String message;

    public AbstractException(String errorcode, String message) {
        super();
        this.errorcode = errorcode;
        this.message = message;
    }
}
