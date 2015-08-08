package com.haritonova.contacts.dao;

import com.haritonova.contacts.entity.Contact;
import com.haritonova.contacts.exception.DAOException;
import com.haritonova.contacts.exception.PoolConnectionException;
import com.haritonova.contacts.pool.Pool;
import com.haritonova.contacts.utils.SortType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  Veronica_Haritonova
 *
 * Class, which implements interface IContactDAO and realize operations with table of contacts in database
 */
public class ContactDAOImpl implements IContactDAO{
    private static final String SQL_INSERT_CONTACT = "INSERT INTO contact (name, surname, login, email, phoneNumber) "
            + "VALUES (?,?,?,?,?) ";
    private static final String SQL_SELECT_CONTACTS = "SELECT c.name, c.surname, c.login, c.email, c.phoneNumber " +
            "FROM contact c ";
    private static final String SQL_ORDER_BY_NAME = " ORDER BY c.name ";
    private static final String SQL_ORDER_BY_SURNAME = " ORDER BY c.surname ";
    private static final String SQL_ORDER_BY_LOGIN = " ORDER BY c.login ";
    private static final String SQL_LIMIT = " LIMIT ?, ? ";
    private static final String SQL_COUNT_ALL = "SELECT COUNT(*) FROM contact ";

    /**
     * Get list of contacts of defined amount starting from defined place
     *
     * @param from
     * @param amount
     * @param sortType
     * @return list of contacts
     * @throws DAOException
     */
    public List<Contact> getContactList(int from, int amount, SortType sortType) throws DAOException {
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            StringBuffer query = new StringBuffer(SQL_SELECT_CONTACTS);
            addSortCondition(query, sortType);
            query.append(SQL_LIMIT);
            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(query.toString());
            List<Contact> contactList = new ArrayList<Contact>();
            Contact contact;
            while (rs.next()) {
                contact = new Contact(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                contactList.add(contact);
            }
            return contactList;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(cn);
            closeResultSet(rs);
            closeStatement(st);
        }
    }

    /**
     * Save contacts' list into database
     *
     * @param contactList
     * @throws DAOException
     */
    public void saveContactList(List<Contact> contactList) throws DAOException {
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = getConnection();
            ps = cn.prepareStatement(SQL_INSERT_CONTACT);
            for(Contact contact : contactList) {
                ps.setString(1,contact.getName());
                ps.setString(2, contact.getSurname());
                ps.setString(3, contact.getLogin());
                ps.setString(4, contact.getEmail());
                ps.setString(5, contact.getPhoneNumber());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(cn);
            closeStatement(ps);
        }
    }

    /**
     * Get amount of all contacts
     *
     * @return amount
     * @throws DAOException
     */
    @Override
    public int getContactsAmount() throws DAOException {
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            cn = getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(SQL_COUNT_ALL);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            returnConnection(cn);
            closeResultSet(rs);
            closeStatement(st);
        }
    }

    /**
     * Add sorting condition to query
     * @param query
     * @param sortType
     */
    private void addSortCondition(StringBuffer query, SortType sortType) {
        switch (sortType) {
            case NAME:
                query.append(SQL_ORDER_BY_NAME);
                break;
            case SURNAME:
                query.append(SQL_ORDER_BY_SURNAME);
                break;
            case LOGIN:
                query.append(SQL_ORDER_BY_LOGIN);
                break;
            default:
                query.append(SQL_ORDER_BY_NAME);
                break;
        }
    }

    /**
     * Get connection from pool
     * @return connection
     * @throws DAOException
     */
    private Connection getConnection() throws DAOException {
        try {
            return Pool.getPool().getConnection();
        } catch (PoolConnectionException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Return connection to pool
     * @param cn
     */
    private void returnConnection(Connection cn) {
        Pool.getPool().returnConnection(cn);
    }

    /**
     * Close result set
     * @param rs
     * @throws DAOException
     */
    private void closeResultSet(ResultSet rs) throws DAOException {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    /**
     * Close statement
     * @param st
     * @throws DAOException
     */
    private void closeStatement(Statement st) throws DAOException {
        if(st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
}
