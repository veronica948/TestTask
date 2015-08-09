package com.haritonova.contacts.utils;

/**
 * @author  Veronica_Haritonova
 */
public class ControllerUtils {
    public static final int CONTACTS_AMOUNT_ON_PAGE = 10;

    public static int countStartPlace(int page) {
        return (page - 1) * ControllerUtils.CONTACTS_AMOUNT_ON_PAGE + 1;
    }
}
