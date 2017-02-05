package com.packman.controller;

import com.packman.Util.PackmanResponseEntity;
import com.packman.Util.SuccessResponse;
import com.packman.Util.UserLite;
import com.packman.Util.enums.RestSuccessMessage;
import com.packman.Util.enums.ShipmentStatus;
import com.packman.exception.RestErrorMessages;
import com.packman.model.Agent;
import com.packman.model.Shipment;
import com.packman.model.User;
import com.packman.service.implementation.AgentServiceImpl;
import com.packman.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Created by sujaysudheendra on 11/22/15.
 */
@RestController
@EnableWebMvc
@RequestMapping("person")
public class PersonController extends BaseController {

    @Autowired
    AgentServiceImpl agentService;

    @Autowired
    UserServiceImpl userService;

    /*Agent Specific Services*/
    /**
     * URL: person/agent/{agentid}
     * TYPE: GET
     * @param id - agentid of the agent that need to be retrieved
     * @return Json representation of agent object
     */
    @RequestMapping(value = "agent/{agentid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAgentById(@RequestHeader(value="user_id") String socialId, @PathVariable("agentid") Long id) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        Agent agent = agentService.getAgentById(id);
        return new ResponseEntity<Agent>(agent, HttpStatus.OK);
    }

   /**
    * URL: person/agent/save
    * TYPE: POST
    * @param agent - json representation of agent object
    * @return http status
    */
    @RequestMapping(value = "agent/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveAgent(@RequestHeader(value="user_id") String socialId, @RequestBody Agent agent) {
        agentService.createAgent(agent);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.ADD_USER.getMessage()));
    }

    /**
     * URL: person/agent/update
     * TYPE: POST
     * @param agent - json representation of agent object
     * @return http status
     */
    @RequestMapping(value = "agent/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateAgent(@RequestHeader(value="user_id") String socialId, @RequestBody Agent agent) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        agentService.updateAgent(agent);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.AGENT_UPDATED.getMessage()));
    }

    /**
     * URL: person/agent/{agentid}
     * TYPE: DELETE
     * @param id - id of agent to be deleted
     * @return http status
     */
    @RequestMapping(value = "agent/{agentid}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> removeAgent(@RequestHeader(value="user_id") String socialId, @PathVariable("agentid") Long id) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        agentService.deleteAgent(id);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.AGENT_FOUND.getMessage()));
    }

    /**
     * URL: perosn/agent/agentid/status/{status}
     * TYPE: GET
     * @param agentId - id of agent for which shipment list need to retrieved
     * @param status - status for which shipment list need to be retrieved
     * @return list of shipment
     */
    @RequestMapping(value = "agent/{id}/status/{status}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAgentShipmentsByStatus(@RequestHeader(value="user_id") String socialId, @PathVariable("id") Long agentId, @PathVariable("status") ShipmentStatus status) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        List<Shipment> shipmentList = agentService.getAgentShipmentsByStatus(agentId, status);
        return new ResponseEntity<List<Shipment>>(shipmentList, HttpStatus.OK);
    }

    /**
     * URL: person/response/agent/{agentId}/shipment/{agentId}
     * TYPE: POST
     * @param agentId - id of agent accepting the shipment
     * @param shipmentId - id of shipment accepted
     * @return http status
     */
    @RequestMapping(value = "response/agent/{agentId}/shipment/{agentId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> receiveAgentResponse(@RequestHeader(value="user_id") String socialId, @PathVariable("agentId") Long agentId, @PathVariable("agentId") Long shipmentId) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        agentService.receiveAgentResponse(agentId, shipmentId);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.AGENT_RESPONSE.getMessage()));
    }

    /*User Specific Services*/


    @RequestMapping(value = "user/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getUserById(@RequestHeader(value="user_id") String socialId, @PathVariable("id") Long id) {
        if(!super.authenticate(socialId)) {
            System.out.println("coudlnot authenticate");
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("authenticated");
        User user = userService.getUserById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * URL: person/user/save
     * TYPE: POST
     * @param user - json representation of user object
     * @return http status
     */
    @RequestMapping(value = "user/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestHeader(value="user_id") String socialId, @RequestBody User user) {
        userService.createUser(user);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.ADD_USER.getMessage()));
    }

    /**
     * URL: person/agent/update
     * TYPE: POST
     * @param user - json representation of user object
     * @return http status
     */
    @RequestMapping(value = "user/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateUser(@RequestHeader(value="user_id") String socialId, @RequestBody User user) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        userService.updateUser(user);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.USER_UPDATED.getMessage()));
    }

    /**
     * URL: person/user/{agentid}
     * TYPE: DELETE
     * @param id - id of agent to be deleted
     * @return http status
     */
    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> removeUser(@RequestHeader(value="user_id") String socialId, @PathVariable("id") Long id) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        userService.deleteUser(id);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.USER_DELETED.getMessage()));
    }

    /**
     * URL: person/user/all
     * @return list of all users
     */
    @RequestMapping(value = "user/all", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getAllUsers(@RequestHeader(value="user_id") String socialId) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }


    /**
     *
     * @param socialId socialId to authenticate the user
     * @param phoneNo
     * @return
     */
    @RequestMapping(value = "/{phoneNo}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getReceiverByPhoneNo(@RequestHeader(value="user_id") String socialId, @PathVariable("phoneNo") String phoneNo) {
        if(!super.authenticate(socialId)) {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        User user = userService.getUserByPhoneNo(phoneNo);
        if(null != user) {
            UserLite userLite = new UserLite(user.getUserId(), user.getPerson().getFirstName(), user.getPerson().getLastName(), user.getDefaultAddress());
            return new ResponseEntity<UserLite>(userLite, HttpStatus.OK);
        }
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestErrorMessages.NO_USER_FOUND.getMessage()));

    }
}
