package com.haritonova.contacts.dao;

import com.haritonova.contacts.entity.Contact;
import com.haritonova.contacts.exception.DAOException;
import com.haritonova.contacts.utils.SortType;

import java.util.List;

/**
 * @author  Veronica_Haritonova
 *
 * Interface to perform operations with table of contacts in database
 */
public interface IContactDAO {
    /**
     * Get list of contacts of defined amount starting from defined place
     * @param from
     * @param amount
     * @param sortType
     * @throws DAOException
     * @return list of contacts
     */
    List<Contact> getContactList(int from, int amount, SortType sortType) throws DAOException;

    /**
     * Save contacts' list into database
     * @param contactList
     * @throws DAOException
     */
    void saveContactList(List<Contact> contactList) throws DAOException;

    /**
     * Get amount of all contacts
     * @return amount
     * @throws DAOException
     */
    int getContactsAmount() throws DAOException;
}
