package com.packman.service.implementation;


import com.packman.Util.enums.ShipmentStatus;
import com.packman.Util.notificationManager.NotificationHub;
import com.packman.dao.daointerfaces.PersonDAO;
import com.packman.dao.daointerfaces.UserDAO;
import com.packman.dao.implementation.PersonDAOImpl;
import com.packman.dao.implementation.ShipmentDAOImpl;
import com.packman.exception.PackmanException;
import com.packman.exception.RestErrorMessages;
import com.packman.model.Shipment;
import com.packman.service.serviceinterfaces.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class for Shipment Service Implementation.
 * <p>
 * Created by mlshah on 11/19/15.
 */
@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    ShipmentDAOImpl shipmentDAO;

    @Autowired
    AgentServiceImpl agentService;

    @Autowired
    PickUpServiceImpl pickUpService;

    public void updateShipment(Shipment shipment) {
        try {
            shipmentDAO.update(shipment);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.UPDATE_SHIPMENT_FAILED, ex);
        }
    }

    public void createShipment(Shipment shipment) {
        try {
            if (shipment.getStatus() == null)
                shipment.setStatus(ShipmentStatus.DRAFT);
            shipmentDAO.saveOrUpdateShipment(shipment);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.SAVE_SHIPMENT_FAILED, ex);
        }
    }

    public void deleteShipment(Long shipmentId) {
        Shipment shipment;
        try {
            shipment = shipmentDAO.getShipmentById(shipmentId);
            checkNullAndThrow(shipment, RestErrorMessages.NO_SHIPMENT_FOUND);
            if (shipment.getStatus().equals(ShipmentStatus.DRAFT.getStatus()))
                shipmentDAO.remove(shipment);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.DELETE_SHIPMENT_FAILED, ex);
        }
    }

    public Shipment getShipmentByShipmentId(Long id) {
        Shipment shipment;
        try {
            shipment = shipmentDAO.getShipmentById(id);
            checkNullAndThrow(shipment, RestErrorMessages.NO_SHIPMENT_FOUND);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.NO_SHIPMENT_FOUND, ex);
        }
        return shipment;
    }

    public List<Shipment> getShipmentListBySenderId(Long senderId) {
        List<Shipment> shipments;
        try {
            shipments = shipmentDAO.getShipmentListBySenderId(senderId);
            checkNullAndThrow(shipments, RestErrorMessages.SHIPMENT_BY_SENDER_FAILED);
        } catch(Exception ex) {
            throw new PackmanException(RestErrorMessages.SHIPMENT_BY_SENDER_FAILED, ex);
        }
        return shipments;
    }

    public List<Shipment> getShipmentListByReceiverId(Long receiverId) {
        List<Shipment> shipments;
        try {
            shipments = shipmentDAO.getShipmentListByReceiverId(receiverId);
            checkNullAndThrow(shipments, RestErrorMessages.SHIPMENT_BY_RECEIVER_FAILED);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.SHIPMENT_BY_RECEIVER_FAILED, ex);
        }
        return shipments;
    }

    public double getEstimate(Long shipmentId) {
        //TODO: write complete logic to calculate the cost
        return 0;
    }

    public List<Shipment> getShipmentListByStatus(Long userId, String status) {
        List<Shipment> shipments;
        try {
            shipments = shipmentDAO.getShipmentListByStatus(userId, status);
            checkNullAndThrow(shipments, RestErrorMessages.SHIPMENT_BY_STATUS_FAILED);
        } catch (Exception ex) {
            throw new PackmanException(RestErrorMessages.SHIPMENT_BY_STATUS_FAILED, ex);
        }
        return shipments;
    }

    private void checkNullAndThrow(Object obj, RestErrorMessages errorMessage){
        if(obj == null) {
            throw new PackmanException(errorMessage);
        }
    }
}
