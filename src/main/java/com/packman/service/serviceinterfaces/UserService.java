package com.packman.service.serviceinterfaces;

import com.packman.Util.enums.NotificationMessage;
import com.packman.model.Shipment;
import com.packman.model.User;

import java.util.List;

/**
 * Interface for User Service.
 * Created by mlshah on 11/18/15.
 */
public interface UserService {

    void createUser(User user);

    User verifyUser(String userToken);

    void updateUser(User user);

    void authenticateUser();

    void deleteUser(Long userId);

    User getUserById(Long id);

    void loginUser();

    void logoutUser();

    void notifyUser(Shipment shipment, NotificationMessage message);

    List<User> getAllUsers();

    boolean isUserPresent(String socialId);

    User getUserByPhoneNo(String phoneNo);
}
