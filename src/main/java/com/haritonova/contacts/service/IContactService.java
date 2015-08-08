package com.haritonova.contacts.service;

import com.haritonova.contacts.entity.Contact;
import com.haritonova.contacts.exception.ServiceException;
import com.haritonova.contacts.utils.SortType;

import java.util.List;

/**
 * @author  Veronica_Haritonova
 */
public interface IContactService {
    /**
     * Get list of contacts of defined amount starting from defined place
     * @param from
     * @param amount
     * @param sortCondition
     * @throws ServiceException
     * @return list of contacts
     */
    List<Contact> getContactList(int from, int amount, String sortCondition) throws ServiceException;
    /**
     * Save contacts' list
     * @param contactList
     * @throws ServiceException
     */
    void saveContactList(List<Contact> contactList) throws ServiceException;
    /**
     * Get amount of all contacts
     * @return amount
     * @throws ServiceException
     */
    int getContactsAmount() throws ServiceException;
}
