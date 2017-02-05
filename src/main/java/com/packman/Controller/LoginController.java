package com.packman.controller;


import com.packman.model.Agent;
import com.packman.model.AuthToken;
import com.packman.model.User;
import com.packman.service.serviceinterfaces.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by sujaysudheendra on 11/22/15.
 */
@RestController
@RequestMapping("person")
@EnableWebMvc
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * URL: person/token
     * TYPE: POST
     * @param authToken - auth token
     * @return http status
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> saveUser(@RequestBody AuthToken authToken) {
        System.out.println("authToken: " + authToken.getIdToken());
        User user = userService.verifyUser(authToken.getIdToken());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
