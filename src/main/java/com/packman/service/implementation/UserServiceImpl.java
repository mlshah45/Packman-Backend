package com.packman.service.implementation;

import com.google.api.client.auth.openidconnect.IdToken;
import com.packman.Util.GoogleAuth;
import com.packman.Util.enums.NotificationMessage;
import com.packman.Util.notificationManager.NotificationHub;
import com.packman.dao.daointerfaces.UserDAO;
import com.packman.exception.PackmanException;
import com.packman.exception.RestErrorMessages;
import com.packman.model.Person;
import com.packman.model.Shipment;
import com.packman.model.User;
import com.packman.service.serviceinterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mlshah on 11/19/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    public Person showPerson() {
        return new Person("sujay", "Sudheendra", "sujay@gmail.com", "123456777");
    }

    @Override
    public void createUser(User user) {
        try {
            userDAO.addUser(user);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.DUPLICATE_USER, ex);
        }
    }

    @Override
    public User getUserById(Long id) {
        User user;
        try {
            user = userDAO.getUserById(id);
            //checkNullAndThrow(user, RestErrorMessages.NO_USER_FOUND);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.NO_USER_FOUND, ex);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try {
            userDAO.update(user);
            NotificationHub.sendNotificationToUsers(Arrays.asList(user), "user updated");
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.USER_UPDATE_FAILED, ex);
        }
    }

    @Override
    public void authenticateUser() {

    }

    @Override
    public void deleteUser(Long userId) {
        try {
            userDAO.delete(userId);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.USER_DELETE_FAILED, ex);
        }
    }

    @Override
    public User verifyUser(String userToken) {
        GoogleAuth googleAuth = new GoogleAuth();

        final IdToken.Payload payload = googleAuth.verifyToken(userToken);
        System.out.println(payload);
        final String socialId = payload.getSubject().toString();
        User user = userDAO.getUserBySocialId(socialId);
        System.out.println("user found " + user);
        if(user == null) {
            System.out.println("user is null");
            user = new User();
            Person newPerson = new Person(payload.get("family_name").toString(), payload.get("given_name").toString(), payload.get("email").toString(), payload.get("picture").toString(),  payload.getSubject().toString());
            user.setPerson(newPerson);
            user.setActive(Boolean.TRUE);
            try {
                userDAO.addUser(user);
            } catch (Exception ex) {
                throw new PackmanException(RestErrorMessages.ADD_USER_FAILED, ex);
            }
        }
        System.out.println(userToken);
        return user;
    }

    @Override
    public void loginUser() {

    }

    @Override
    public void logoutUser() {

    }

    @Override
    public void notifyUser(Shipment shipment, NotificationMessage message) {
        // TODO: notify user based on message
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try {
            users = userDAO.getAllUsers();
            checkNullAndThrow(users, RestErrorMessages.GET_ALL_USER_FAILED);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.GET_ALL_USER_FAILED, ex);
        }
        return users;
    }

    private void checkNullAndThrow(Object obj, RestErrorMessages errorMessage){
        if(obj == null) {
            throw new PackmanException(errorMessage);
        }
    }

    @Override
    public boolean isUserPresent(String socialId) {
        User user = userDAO.getUserBySocialId(socialId);
        return null != user ? true : false;
    }

    @Override
    public User getUserByPhoneNo(String phoneNo) {
        User user = userDAO.getUserByPhoneNo(phoneNo);
        return user;
    }
}
