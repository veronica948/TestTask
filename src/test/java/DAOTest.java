import com.haritonova.contacts.dao.ContactDAOImpl;
import com.haritonova.contacts.dao.IContactDAO;
import com.haritonova.contacts.entity.Contact;
import com.haritonova.contacts.exception.DAOException;
import com.haritonova.contacts.pool.Pool;
import com.haritonova.contacts.utils.SortType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author  Veronica_Haritonova
 */
public class DAOTest {

    @Test
    public void getPoolTest() {
        Pool pool = Pool.getPool();
        assertNotNull(pool);
    }
    @Test
    public void getContactsTest() throws DAOException {
        IContactDAO contactDAO = new ContactDAOImpl();
        List<Contact> contactList = contactDAO.getContactList(1, 10, SortType.NAME);
        assertNotNull(contactList);
    }
    @Test
    public void addContacts() throws DAOException {
        Contact contact = new Contact("Maria","Way", "masha", "jfh@gmail.com", "353535");
        IContactDAO contactDAO = new ContactDAOImpl();
        int previousAmount = contactDAO.getContactsAmount();
        contactDAO.saveContactList(new ArrayList<Contact>() {
            {
                this.add(contact);
            }
        });
        int newAmount = contactDAO.getContactsAmount();
        assertEquals(previousAmount + 1,newAmount);
    }
}
