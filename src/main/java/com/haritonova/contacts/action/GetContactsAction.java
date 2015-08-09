package com.haritonova.contacts.action;

import com.haritonova.contacts.entity.Contact;
import com.haritonova.contacts.exception.ActionException;
import com.haritonova.contacts.exception.ServiceException;
import com.haritonova.contacts.manager.ConfigManager;
import com.haritonova.contacts.service.ContactServiceImpl;
import com.haritonova.contacts.service.IContactService;
import com.haritonova.contacts.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author  Veronica_Haritonova
 */
public class GetContactsAction implements Action {
    private IContactService contactService;
    {
        contactService = new ContactServiceImpl();
    }
    private static final String PARAM_SORT_CONDITION = "sort";
    private static final String PARAM_PAGE = "page";
    private static final String ATTR_CONTACT_LIST = "contactList";
    private static final String ATTR_AMOUNT = "amount";
    private static final String ATTR_PAGE = "page";

    public String execute(HttpServletRequest request) throws ActionException {
        String sortCondition = request.getParameter(PARAM_SORT_CONDITION);

        Integer page = Integer.parseInt(request.getParameter(PARAM_PAGE));
        try {
            List<Contact> contactList = contactService.getContactList(ControllerUtils.countStartPlace(page), ControllerUtils.CONTACTS_AMOUNT_ON_PAGE, sortCondition);
            request.setAttribute(ATTR_CONTACT_LIST, contactList);
            int amount = contactService.getContactsAmount();
            request.setAttribute(ATTR_AMOUNT, amount);
            request.setAttribute(ATTR_PAGE,page);
        } catch (ServiceException e) {
            throw  new ActionException(e);
        }
        return ConfigManager.getProperty("path.page.contacts.list");
    }
}
