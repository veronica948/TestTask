package com.haritonova.contacts.service;

import com.haritonova.contacts.dao.ContactDAOImpl;
import com.haritonova.contacts.dao.IContactDAO;
import com.haritonova.contacts.entity.Contact;
import com.haritonova.contacts.exception.DAOException;
import com.haritonova.contacts.exception.ServiceException;
import com.haritonova.contacts.utils.SortType;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author  Veronica_Haritonova
 */
public class ContactServiceImpl implements IContactService{
    Logger logger = Logger.getLogger(ContactServiceImpl.class);
    private IContactDAO contactDAO;
    {
        contactDAO = new ContactDAOImpl();
    }
    /**
    * Get list of contacts of defined amount starting from defined place
    * @param from
    * @param amount
    * @param sortCondition
    * @throws ServiceException
    * @return list of contacts
    */
    public List<Contact> getContactList(int from, int amount,String sortCondition) throws ServiceException {
        try {
            SortType sortType = SortType.NAME;
            if(sortCondition != null) {
                if(!sortCondition.isEmpty()) {
                    sortType = SortType.valueOf(sortCondition.toUpperCase());
                }
            }
            return contactDAO.getContactList(from,amount,sortType);
        } catch (DAOException | IllegalArgumentException e) {
            logger.error(e);
            throw new ServiceException(e);

        }
    }
    /**
     * Save contacts' list
     * @param contactList
     * @throws ServiceException
     */
    public void saveContactList(List<Contact> contactList) throws ServiceException {
        try {
            contactDAO.saveContactList(contactList);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);

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
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
