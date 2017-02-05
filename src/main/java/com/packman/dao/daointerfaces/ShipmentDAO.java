package com.packman.dao.daointerfaces;

import com.packman.model.Shipment;

import java.util.List;

/**
 * DAO Class for Shipment Entity.
 * <p>
 * Created by mlshah on 11/19/15.
 */
public interface ShipmentDAO extends GenericDAO<Shipment, Long> {

    List<Shipment> getShipmentListByReceiverId(Long receiverId);

    List<Shipment> getShipmentListByStatus(Long userId, String status);

    List<Shipment> getShipmentListBySenderId(Long senderId);

    boolean updateShipment(Shipment shipment);

    boolean saveOrUpdateShipment(Shipment shipment);

    boolean delete(Shipment shipment) throws Exception;

    List<Shipment> getShipmentList(Long senderId) throws Exception;

    Shipment getShipmentById(Long id) throws Exception;

    boolean addShipment(Shipment shipment) throws Exception;

    //TODO: Currently uses the generic methods. Add Shipment Specific methods for future implementation.
}
