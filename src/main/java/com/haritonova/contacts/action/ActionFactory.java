package com.haritonova.contacts.action;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public static Action defineCommand(HttpServletRequest request) {
        String actionName = request.getParameter("action");
        Action current = null;
        if(actionName == null) {
            return new EmptyAction();
        }
        try {
            ActionType type = ActionType.valueOf(actionName.toUpperCase());
            current = type.getAction();
        } catch (IllegalArgumentException e) {
            current = new EmptyAction();
        }
        return current;
    }
}
