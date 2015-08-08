package com.haritonova.contacts.action;

import com.haritonova.contacts.exception.ActionException;
import com.haritonova.contacts.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Пользователь on 07.10.2014.
 */
public class EmptyAction implements Action {
    public String execute(HttpServletRequest request) throws ActionException {
        return ConfigManager.getProperty("path.page.index");
    }
}
