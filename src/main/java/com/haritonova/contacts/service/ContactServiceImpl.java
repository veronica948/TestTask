package com.haritonova.contacts.service;

import com.haritonova.contacts.dao.ContactDAOImpl;
import com.haritonova.contacts.dao.IContactDAO;
import com.haritonova.contacts.entity.Contact;
import com.haritonova.contacts.exception.DAOException;
import com.haritonova.contacts.exception.ServiceException;
import com.haritonova.contacts.utils.SortType;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author  Veronica_Haritonova
 */
public class ContactServiceImpl implements IContactService{
    private IContactDAO contactDAO;
    {
        contactDAO = new ContactDAOImpl();
    }
    public List<Contact> getContactList(int from, int amount,String sortCondition) throws ServiceException {
        try {
            SortType sortType = SortType.NAME;
            if(sortCondition != null) {
                if(!sortCondition.isEmpty()) {
                    sortType = SortType.valueOf(sortCondition);
                }
            }
            return contactDAO.getContactList(from,amount,sortType);
        } catch (DAOException | IllegalArgumentException e) {
            throw new ServiceException(e);
            //logger
        }
    }
    public void saveContactList(List<Contact> contactList) throws ServiceException {
        try {
            contactDAO.saveContactList(contactList);
        } catch (DAOException e) {
            throw new ServiceException(e);
            //logger
        }
    }

    /**
     * Get amount of all contacts
     *
     * @return amount
     * @throws ServiceException
     */
    @Override
    public int getContactsAmount() throws ServiceException {
        try {
            return contactDAO.getContactsAmount();
        } catch (DAOException e) {
            //logger
            throw new ServiceException(e);
        }
    }
}
