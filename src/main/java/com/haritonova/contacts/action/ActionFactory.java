package com.haritonova.contacts.action;

import javax.servlet.http.HttpServletRequest;

/**
 * @author  Veronica_Haritonova
 */
public class ActionFactory {
    private static final String PARAM_ACTION = "action";

    public static Action defineCommand(HttpServletRequest request) {
        String actionName = request.getParameter(PARAM_ACTION);
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
