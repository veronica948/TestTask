package com.haritonova.contacts.action;


import com.haritonova.contacts.exception.ActionException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author  Veronica_Haritonova
 */
public interface Action {
    String execute(HttpServletRequest request) throws ActionException;
}
