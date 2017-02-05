package com.packman.dao.daointerfaces;


import java.io.Serializable;
import java.util.List;

/**
 * The Interface GenericDAO. This defines the functionality in the GenericDAO
 * class.
 *
 * @param <T>  generic type for object that is going into database
 * @param <ID> generic type for the ID for the object
 */
public interface GenericDAO<T, ID extends Serializable> {

    /**
     * Saves the object in the database
     *
     * @param entity the object to go into the database
     */
    void add(T entity);


    /**
     * Delete the object from the database
     *
     * @param entity the object to be deleted
     */
    void remove(T entity);

    /**
     * find all the objects in database
     *
     * @return the List of all objects
     */
    List<T> getAll();

    /**
     * Finds the Object with the specified key
     *
     * @param key of the Object that needs to be retrieved.
     * @return T Generic object returned from the database.
     */
    T find(ID key);

    /**
     * Saves or updates the Object depending on whether it is already present.
     *
     * @param entity The Object that needs to be saved or updated.
     */
    void saveOrUpdate(T entity);

    /**
     * Updates the already existing Object into the Database.
     *
     * @param entity The entity Object that is to be updated.
     */
    void update(T entity);
}
