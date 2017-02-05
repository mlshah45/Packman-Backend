package com.packman.dao.implementation;

import com.packman.dao.daointerfaces.GenericDAO;
import com.packman.exception.GenericDAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * This class is an Implementation of methods the DAO should have.
 *
 * @param <E> the object going into the database
 * @param <K> the ID for the object going into database
 */
@Repository
@Component
public abstract class GenericDAOImpl<E, K extends Serializable>
        implements GenericDAO<E, K> {

    // i18n Strings
    private static final String ENTITY_NULL = "Writing to Database Failed:  Object to write to database is null.";
    private static final String LIST_NULL = "Insertion Failed. List to insert is null.";
    private static final String LIST_EMPTY = "Insertion Failed. List to insert is empty.";
    private static final String SESSION_NULL = "Connection to Database Failed: Session could not be established.";
    private static final String TRANS_NULL = "Connection to Database Failed: Transaction could not be started.";
    private static final String GEN_DB_ERROR = "Generic Database Error: ";
    private static final String QUERY_ERROR = "Database Query Failure: Query is null.";
    private static final String DELETE_NULL = "Database Query Failure: Entity to delete is null.";
    private static final String DEL_CLASS_NULL = "Database Query Failure: Object to search by is null.";
    protected Class<? extends E> daoType;
    private Serializable id;
    //private Transaction transaction;
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * By defining this class as abstract, we prevent Spring from creating
     * instance of this class If not defined as abstract,
     * getClass().getGenericSuperClass() would return Object. There would be
     * exception because Object class does not hava constructor with parameters.
     */
    public GenericDAOImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    /**
     * Intermediate function to get the current session.
     *
     * @return session of the current transaction.
     */
    protected Session currentSession() {
        Session session = this.sessionFactory.getCurrentSession();
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    /**
     * Adds the entity into the database.
     *
     * @param entity to be added to the database.
     */
    @Override
    public void add(E entity) {
        Transaction transaction = null;
        try {
            transaction = currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            id = currentSession().save(entity);
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            //TODO: handle exception in a better way
            throw new GenericDAOException(he.getCause().toString());
        }

        currentSession().close();
    }

    /**
     * Saves or updates the Object depending on whether it is already present.
     *
     * @param entity The Object that needs to be saved or updated.
     */
    @Override
    public void saveOrUpdate(E entity) {
        Transaction transaction = null;
        try {
            transaction = currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            currentSession().saveOrUpdate(entity);
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            throw new GenericDAOException(he.getCause().toString());
        }
        currentSession().close();
    }

    /**
     * Updates the already existing Object into the Database.
     *
     * @param entity The entity Object that is to be updated.
     */
    @Override
    public void update(E entity) {
        Transaction transaction = null;
        try {
            transaction = currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            currentSession().update(entity);
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            throw new GenericDAOException(he.getCause().toString());
        }
        currentSession().close();
    }

    /**
     * Delete the object from the database
     *
     * @param entity the object to be deleted
     */
    @Override
    public void remove(E entity) {
        Transaction transaction = null;
        try {
            transaction = currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            currentSession().delete(entity);
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            throw new GenericDAOException(he.getCause().toString());
        }
        currentSession().close();
    }

    /**
     * Finds the Object with the specified key
     *
     * @param key of the Object that needs to be retrieved.
     * @return T Generic object returned from the database.
     */
    @Override
    public E find(K key) {
        Transaction transaction = null;
        E example;
        try {
            transaction = currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            example = (E) currentSession().get(daoType, key);
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            throw new GenericDAOException(he.getCause().toString());
        }
        currentSession().close();
        return example;
    }

    /**
     * find all the objects in database
     *
     * @return the List of all objects
     */
    @Override
    public List<E> getAll() {
        Transaction transaction = null;
        List<E> entityList;
        try {
            transaction = currentSession().beginTransaction();
            if (transaction == null) {
                throw new GenericDAOException(TRANS_NULL);
            }
            entityList = currentSession().createCriteria(daoType).list();
            if(!transaction.wasCommitted())
            transaction.commit();

        } catch (final HibernateException he) {
            if (transaction != null) {
                // Transaction has to be rolled back when exception is thrown
                transaction.rollback();
            }
            throw new GenericDAOException(he.getCause().toString());
        }
        currentSession().close();
        return entityList;
    }
}
