package com.haritonova.contacts.action;


import com.haritonova.contacts.exception.ActionException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 07.10.2014.
 */
public interface Action {
    String execute(HttpServletRequest request) throws ActionException;
}
