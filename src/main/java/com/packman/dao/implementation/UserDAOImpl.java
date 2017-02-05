package com.packman.dao.implementation;

import com.packman.dao.daointerfaces.UserDAO;
import com.packman.exception.GenericDAOException;
import com.packman.model.Shipment;
import com.packman.model.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDAO Implementation.
 * <p>
 * Created by mlshah on 11/19/15.
 */
@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {

    // i18n Strings
    private static final String TRANS_NULL = "Connection to Database Failed: Transaction could not be started.";
    private static final String GEN_DB_ERROR = "Generic Database Error: ";
    private Transaction transaction;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addUser(User user) throws Exception {
        super.add(user);
        return true;
    }

    @Override
    public User getUserById(Long id) throws Exception {
        return super.find(id);
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return super.getAll();
    }

    @Override
    public boolean delete(Long userId) throws Exception {
        User user;
        user = getUserById(userId);
        user.setActive(false);
        updateUser(user);
        return true;
    }

    @Override
    public boolean saveOrUpdateUser(User user) {
        super.saveOrUpdate(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        super.update(user);
        return true;
    }

    @Override
    public User getUserBySocialId(String socialId) {
        Transaction transaction = null;
        List<User> users;
        try {
            transaction = super.currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            Criteria userSelection = super.currentSession().createCriteria(User.class, "user");
            userSelection.createAlias("user.person", "person");
            userSelection.add(Restrictions.eq("person.socialId", socialId));

            users = (List<User>) userSelection.list();
            if(users.size() == 0) {
                super.currentSession().close();
                return null;
            }
            if(!transaction.wasCommitted())
                transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            System.out.println("exception " + he);
            throw new GenericDAOException(GEN_DB_ERROR);
        }
        super.currentSession().close();
        return users.get(0);
    }

    @Override
    public User getUserByPhoneNo(String phoneNo){
        Transaction transaction = null;
        List<User> users;
        try {
            transaction = super.currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            Criteria userSelection = super.currentSession().createCriteria(User.class, "user");
            userSelection.createAlias("user.person", "person");
            userSelection.add(Restrictions.eq("person.phone", phoneNo));

            users = (List<User>) userSelection.list();
            if(users.size() == 0) {
                super.currentSession().close();
                return null;
            }
            if(!transaction.wasCommitted())
                transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            System.out.println("exception " + he);
            throw new GenericDAOException(GEN_DB_ERROR);
        }
        super.currentSession().close();
        return users.get(0);
    }

}
