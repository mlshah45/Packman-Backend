package com.packman.dao.implementation;

import com.packman.dao.daointerfaces.PersonDAO;
import com.packman.model.Person;

import java.util.List;

/**
 * PersonDAO Implementation.
 * <p>
 * Created by RushikeshParadkar on 11/19/15.
 */
public class PersonDAOImpl extends GenericDAOImpl<Person, Long> implements PersonDAO {

    public boolean addPerson(Person person) throws Exception {
        super.add(person);
        return true;
    }

    public Person getPersonById(Long id) throws Exception {
        return super.find(id);
    }

    public List<Person> getPersonList() throws Exception {
        return super.getAll();
    }

    public boolean delete(Person person) throws Exception {
        super.remove(person);
        return true;
    }

    public boolean saveOrUpdatePerson(Person person) {
        super.saveOrUpdate(person);
        return true;
    }

    public boolean updatePerson(Person person) {
        super.update(person);
        return true;
    }
}
