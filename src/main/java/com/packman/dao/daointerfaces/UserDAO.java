package com.packman.dao.daointerfaces;

import com.packman.model.User;

import java.util.List;

/**
 * DAO for User Entity.
 * <p>
 * Created by RushikeshParadkar on 11/19/15.
 */
public interface UserDAO extends GenericDAO<User, Long> {

    boolean addUser(User user) throws Exception;

    User getUserBySocialId(String socialId);

    User getUserById(Long id) throws Exception;

    List<User> getAllUsers() throws Exception;

    boolean delete(Long id) throws Exception;

    boolean saveOrUpdateUser(User user);

    boolean updateUser(User user);
    //TODO: Currently uses the generic methods. Add User Specific methods for future implementation.

    User getUserByPhoneNo(String phoneNo);
}
