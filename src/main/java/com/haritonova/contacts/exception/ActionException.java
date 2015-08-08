package com.haritonova.contacts.exception;

/**
 * Created by Пользователь on 01.12.2014.
 */
public class ActionException extends Exception {
    public ActionException(String message) {
        super(message);
    }
    public ActionException(Throwable e) {
        super(e.getMessage(),e);
    }
    public ActionException(String message, Throwable e) {
        super(message,e);
    }
}
