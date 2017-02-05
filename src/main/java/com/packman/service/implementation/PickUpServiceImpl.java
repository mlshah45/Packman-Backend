package com.packman.service.implementation;

import com.google.common.collect.Iterables;
import com.packman.Util.enums.NotificationMessage;
import com.packman.Util.enums.ShipmentStatus;
import com.packman.dao.implementation.ShipmentDAOImpl;
import com.packman.exception.PackmanException;
import com.packman.exception.RestErrorMessages;
import com.packman.model.Agent;
import com.packman.model.Shipment;
import com.packman.service.serviceinterfaces.PickUpService;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by RushikeshParadkar on 11/19/15.
 */
@Service
public class PickUpServiceImpl implements PickUpService {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ShipmentDAOImpl shipmentDAO;

    @Autowired
    AgentServiceImpl agentService;

    public Long schedulePickup(Long userId, Long shipmentId) {
        try {
            Shipment shipment = shipmentDAO.getShipmentById(shipmentId);
            checkNullAndThrow(shipment, RestErrorMessages.NO_SHIPMENT_FOUND);
            shipment.setStatus(ShipmentStatus.READY);
            agentService.notifyAllAgents(shipment);
            shipmentDAO.updateShipment(shipment);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.REQ_PICKUP_FAILED, ex);
        }
        return shipmentId;
    }

    public Long cancelPickup(Long shipmentId) {
        try {
            Shipment shipment = shipmentDAO.getShipmentById(shipmentId);
            checkNullAndThrow(shipment, RestErrorMessages.NO_SHIPMENT_FOUND);
            boolean cancelStatus = false;
            Agent agent = null;

            if (shipment.getStatus().equals(ShipmentStatus.ASSIGNED.getStatus())) {
                cancelStatus = true;

                agent = Iterables.get(shipment.getAgentList(), 0);
                agentService.removeShipment(agent, shipment);
                agentService.notifyAssignedAgent(agent, shipment, NotificationMessage.CANCELPICKUP);
            }
            if (shipment.getStatus().equals(ShipmentStatus.READY.getStatus())) {
                cancelStatus = true;
            }

            if (cancelStatus) {
                shipment.setStatus(ShipmentStatus.DRAFT);
                shipmentDAO.updateShipment(shipment);
                userService.notifyUser(shipment, NotificationMessage.CANCELPICKUP);
            }
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.CANCEL_PICKUP_FAILED, ex);
        }
        return shipmentId;
    }

    public Long reschedulePickup(Long userId, Long shipmentId, String pickupTime) {
        Long resheduleShipmentId = cancelPickup(shipmentId);
        try {
            Shipment shipment = shipmentDAO.getShipmentById(resheduleShipmentId);
            checkNullAndThrow(shipment, RestErrorMessages.NO_SHIPMENT_FOUND);
            DateTimeFormatter formater = DateTimeFormat.forPattern("YYYY-MM-dd'T'HH:mm:ss");
            LocalDateTime rescheduleTime = LocalDateTime.parse(pickupTime, formater);
            shipment.setPickupTime(rescheduleTime);
            shipmentDAO.updateShipment(shipment);
            schedulePickup(userId, resheduleShipmentId);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.RESCHEDULE_PICKUP_FAILED, ex);
        }
        return resheduleShipmentId;
    }

    private void checkNullAndThrow(Object obj, RestErrorMessages errorMessage){
        if(obj == null) {
            throw new PackmanException(errorMessage);
        }
    }
}
