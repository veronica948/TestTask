package com.haritonova.contacts.action;

import com.haritonova.contacts.exception.ActionException;
import com.haritonova.contacts.manager.ConfigManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author  Veronica_Haritonova
 */
public class EmptyAction implements Action {
    public String execute(HttpServletRequest request) throws ActionException {
        return ConfigManager.getProperty("path.page.index");
    }
}
