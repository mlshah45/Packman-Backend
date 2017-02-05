package com.packman.dao.daointerfaces;

import com.packman.model.Person;

/**
 * DAO Class for Person Entity
 * <p>
 * Created by RushikeshParadkar on 11/19/15.
 */
public interface PersonDAO extends GenericDAO<Person, Long> {

    //TODO: Currently uses the generic methods. Add Person Specific methods for future implementation.
}
