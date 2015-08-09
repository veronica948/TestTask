package com.haritonova.contacts.action;

import com.haritonova.contacts.entity.Contact;
import com.haritonova.contacts.exception.ActionException;
import com.haritonova.contacts.exception.DAOException;
import com.haritonova.contacts.exception.ServiceException;
import com.haritonova.contacts.manager.ConfigManager;
import com.haritonova.contacts.service.ContactServiceImpl;
import com.haritonova.contacts.service.IContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author  Veronica_Haritonova
 */
public class ImportContactsAction implements Action {
    private IContactService contactService;
    {
        contactService = new ContactServiceImpl();
    }
    private static final String ATTR_SUCCESS_OPERATION = "successfulOperation";
    private static final String MSG_SUCCESS = "success";
    private static final String PARAM_FILE = "file";
    private static final String MSG_EMPTY = "You haven't chosen the file";
    private static final String ATTR_EMPTY = "emptyFile";
    private static final String ATTR_BUSY = "busy";
    private static final String MSG_BUSY = "This operation ia not available now, because it's performed by other user. Try again later.";
    private ReentrantLock lock = new ReentrantLock();
    public String execute(HttpServletRequest request) throws ActionException {
        BufferedReader bufferedReader = null;
        try {
            if(lock.tryLock()) {
                Part part = request.getPart(PARAM_FILE);
                if(part.getSize() != 0) {
                    bufferedReader = new BufferedReader(new InputStreamReader(part.getInputStream()));
                    String line;
                    String delims = ",";
                    String[] items;
                    List<Contact> contactList = new ArrayList<Contact>();
                    while ((line = bufferedReader.readLine()) != null) {
                        items = line.split(delims);
                        Contact contact = new Contact(items[0], items[1], items[2], items[3], items[4]);
                        contactList.add(contact);
                    }
                    contactService.saveContactList(contactList);
                    request.setAttribute(ATTR_SUCCESS_OPERATION, MSG_SUCCESS);
                } else {
                    request.setAttribute(ATTR_EMPTY,MSG_EMPTY);
                }
                lock.unlock();
            } else {
                request.setAttribute(ATTR_BUSY, MSG_BUSY);
            }
        } catch (IOException | ServiceException | ServletException e) {
            throw new ActionException(e);
        } finally {
                if(bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        return ConfigManager.getProperty("path.page.file.load");
    }
}
