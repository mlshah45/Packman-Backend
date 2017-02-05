package com.packman.controller;

import com.packman.Util.enums.ShipmentStatus;
import com.packman.dao.implementation.AgentDAOImpl;
import com.packman.model.*;
import com.packman.service.implementation.ShipmentServiceImpl;
import com.packman.service.implementation.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Description.
 *
 * @author Rushikesh Paradkar (RP042975)
 */
@RestController
@EnableWebMvc
public class HelloWorldController {
    String message = "Welcome to Spring MVC!";
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    AgentDAOImpl agentDAO;

    @Autowired
    ShipmentServiceImpl shipmentService;
    UserServiceImpl userService = new UserServiceImpl();

    @RequestMapping("/hello")
    public ModelAndView showMessage(@RequestParam(value = "name", required = false, defaultValue = "World") final String name) {
        Transaction transaction = sessionFactory.getCurrentSession().getTransaction();
        transaction.begin();
        Person p = new Person("hello", "helloooo", "helloooooo", "hellooooo", "1");
        Credentials credentials = new Credentials();
        credentials.setPassword("packman123");
        p.setCredentials(credentials);
        Address add1 = new Address("hello", "helloooo", "helloooooo", "hellooooo", "hello", "helloooo", "helloooooo", "hellooooo");
        Collection<Address> alist = new ArrayList<Address>();
        alist.add(add1);
        User user = new User(p);
        Agent agent = new Agent(p, add1);
        user.setAddress(alist);

        BigDecimal cost = new BigDecimal(23.3);
        LocalDateTime pickupTime = new LocalDateTime();

        File file = new File("/Users/RushikeshParadkar/Downloads/apache-tomcat-8.0.28/webapps/docs/images/asf-feather.png");
        byte[] bFile = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Box box = new Box(bFile, 20, 20, 20, 20);

        Shipment shipment = new Shipment(user, user, add1, add1, cost, "INR", ShipmentStatus.ASSIGNED, pickupTime, box);
        Shipment shipment1 = new Shipment(); //(user, user, add1, add1, cost, "USD", "DRAFT", pickupTime, box);

        shipment1.setBox(box);
        shipment1.setFromAddress(add1);
        shipment1.setToAddress(add1);
        shipment1.setPickupTime(pickupTime);
        shipment1.setPrice(cost);
        shipment1.setSender(user);
        shipment1.setReceiver(user);
        shipment1.setStatus(ShipmentStatus.ASSIGNED);

        agent.getAssignedShipments().add(shipment);
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().save(credentials);
        sessionFactory.getCurrentSession().save(p);
        sessionFactory.getCurrentSession().save(shipment);

        transaction.commit();
        sessionFactory.getCurrentSession().close();

        try {
            agentDAO.addAgent(agent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ModelAndView mv = new ModelAndView("helloworld");
        mv.addObject("message", message);
        mv.addObject("name", name);
        return mv;
    }

    @RequestMapping(value = "/show_person", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Person> showUser() {

        Person user = userService.showPerson();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
