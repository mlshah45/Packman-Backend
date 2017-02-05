package com.packman.controller;

import com.packman.service.serviceinterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by mlshah on 5/1/16.
 */
@EnableWebMvc
public class BaseController {

    @Autowired
    private UserService userService;

    public boolean authenticate(String socialId) {
        return userService.isUserPresent(socialId);
    }
}
