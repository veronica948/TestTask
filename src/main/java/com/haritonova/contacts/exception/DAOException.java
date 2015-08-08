package com.haritonova.contacts.exception;

/**
 * Created by Пользователь on 18.11.2014.
 */
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }
    public DAOException(String message, Throwable e) {
        super(message,e);
    }
    public DAOException(Throwable e) {
        super(e);
    }

}
