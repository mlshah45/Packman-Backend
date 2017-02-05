package com.packman.controller;

import com.packman.Util.PackmanResponseEntity;
import com.packman.Util.SuccessResponse;
import com.packman.Util.enums.RestSuccessMessage;
import com.packman.Util.enums.ShipmentStatus;
import com.packman.model.Shipment;
import com.packman.service.implementation.PickUpServiceImpl;
import com.packman.service.implementation.ShipmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Shipment Controller.
 * <p/>
 * Created by mlshah on 11/22/15.
 */
@RestController
@EnableWebMvc
@RequestMapping("/shipments")
public class ShipmentController extends BaseController {

    @Autowired
    ShipmentServiceImpl shipmentService;

    @Autowired
    PickUpServiceImpl pickUpService;

    /**
     * URL: shipments/save
     * TYPE: POST
     * @param shipment - json object of shipment that needs to be created
     * @return httpstatus
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveShipment(@RequestHeader(value="user_id") String socialId, @RequestBody Shipment shipment) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        shipmentService.createShipment(shipment);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.SAVE_SHIPMENT.getMessage()));
    }

    /**
     * URL: shipments/shipmentid
     * TYPE: GET
     * @param id - id of shipment
     * @return returns shipment json object
     */
    @RequestMapping(value = "/{shipmentid}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getShipment(@RequestHeader(value="user_id") String socialId, @PathVariable("shipmentid") Long id) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        Shipment ship = shipmentService.getShipmentByShipmentId(id);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    /**
     * URL: shipments/update
     * TYPE: POST
     * @param shipment - the json object of shipment need to be updated
     * @return http status if successful
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateShipment(@RequestHeader(value="user_id") String socialId, @RequestBody Shipment shipment) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        shipmentService.updateShipment(shipment);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.UPDATE_SHIPMENT.getMessage()));
    }

    /**
     * URL: shipments/shipmentid
     * TYPE: DELETE
     * @param shipmentId - id of the shipment to be deleted
     * @return http status
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "the requested shipment does not exist")
    @RequestMapping(value = "/{shipmentid}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> removeShipment(@RequestHeader(value="user_id") String socialId, @PathVariable("shipmentid") Long shipmentId) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        shipmentService.deleteShipment(shipmentId);
        return new PackmanResponseEntity(new SuccessResponse(HttpStatus.OK, RestSuccessMessage.DELETE_SHIPMENT.getMessage()));
    }

    /**
     * URL: shipments/user/{userid}/sentshipments
     * TYPE: GET
     * @param userId - user id for which the shipments need to be retrieved
     * @return list of sent shipments for the user
     */
    @RequestMapping(value = "/user/{userid}/sentshipments", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getShipmentListBySender(@RequestHeader(value="user_id") String socialId, @PathVariable("userid") Long userId) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        List<Shipment> shipmentList = shipmentService.getShipmentListBySenderId(userId);
        return new ResponseEntity<List<Shipment>>(shipmentList, HttpStatus.OK);
    }

    /**
     * URL: shipments/user/{userid}/incomingshipments
     * TYPE: GET
     * @param userId - user id for which the shipments need to be retrieved
     * @return list of awaited shipments for the user
     */
    @RequestMapping(value = "/user/{userid}/incomingshipments", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getShipmentListByReceiver(@RequestHeader(value="user_id") String socialId, @PathVariable("userid") Long userId) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        List<Shipment> shipmentList = shipmentService.getShipmentListByReceiverId(userId);
        return new ResponseEntity<List<Shipment>>(shipmentList, HttpStatus.OK);
    }

    /**
     *URL: shipments/user/{userid}/status/{status}
     * TYPE: GET
     * @param userId - userid for which the request is made
     * @param status - status string on which shipments need to retrieved one of ShipmentStatus allowed value
     * @return list of shipments that are having the status mentioned
     */
    @RequestMapping(value = "/user/{userid}/status/{status}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getShipmentListByStatus(@RequestHeader(value="user_id") String socialId, @PathVariable("userid") Long userId, @PathVariable("status") ShipmentStatus status) {
        if(!super.authenticate(socialId))
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        List<Shipment> shipmentList = shipmentService.getShipmentListByStatus(userId, status.getStatus());
        return new ResponseEntity<List<Shipment>>(shipmentList, HttpStatus.OK);
    }
    
    /**
     * URL: shipments/user/{userid}/requestpickup/{shipmentid}
     * TYPE: GET
     * @param userId - userid for which the request is made
     * @param shipmentId - id of shipment to be scheduled
     * @return id of shipment for which pickup is scheduled
     */
    @RequestMapping(value = "/user/{id}/requestpickup/{shipmentid}", method = RequestMethod.GET, produces = "application/json")
    public Long requestPickup(@PathVariable("id") Long userId, @PathVariable("shipmentid") Long shipmentId) {

        return pickUpService.schedulePickup(userId, shipmentId);
    }

    /**
     * URL: shipments/user/{userid}/cancelpickup/{shipmentid}
     * TYPE: GET
     * @param userId - userid for which the request is made
     * @param shipmentId - id of shipment to be cancelled
     * @return id of shipment for which pickup is cancelled
     */
    @RequestMapping(value = "/user/{id}/cancelpickup/{shipmentid}", method = RequestMethod.GET, produces = "application/json")
    public Long cancelPickup(@PathVariable("id") Long userId, @PathVariable("shipmentid") Long shipmentId) {
        return pickUpService.cancelPickup(shipmentId);
    }

    /**
     * URL: shipments/user/{userid}/reschedule/{shipmentid}/time/{pickuptime}
     * TYPE: GET
     * @param userId - userid for which the request is made
     * @param shipmentId - id of shipment to be rescheduled
     * @param pickupTime - new pickup time for which the shipment needs to be scheduled
     * @return shipmentId which was rescheduled
     */
    @RequestMapping(value = "/user/{id}/reschedule/{shipmentid}/time/{pickuptime}", method = RequestMethod.GET, produces = "application/json")
    public Long reschedulePickup(@PathVariable("id") Long userId, @PathVariable("shipmentid") Long shipmentId, @PathVariable("pickuptime") String pickupTime) {
        return pickUpService.reschedulePickup(userId, shipmentId, pickupTime);
    }
}
