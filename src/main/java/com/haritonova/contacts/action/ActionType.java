package com.haritonova.contacts.action;

/**
 * @author Veronica_Haritonova
 */
public enum ActionType {
    IMPORT_CONTACTS(new ImportContactsAction()),
    GET_CONTACTS(new GetContactsAction()),
    TO_IMPORT_PAGE(new ToImportAction());
    private Action action;
    ActionType(Action action) {
        this.action = action;
    }
    public Action getAction() {
        return action;
    }
}
