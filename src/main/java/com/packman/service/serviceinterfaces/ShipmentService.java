package com.packman.service.serviceinterfaces;

import com.packman.model.Shipment;

import java.util.List;

/**
 * Created by mlshah on 11/18/15.
 */
public interface ShipmentService {

    void updateShipment(Shipment shipment);

    void createShipment(Shipment shipment);

    void deleteShipment(Long shipmentId);

    double getEstimate(Long shipmentId);

    List<Shipment> getShipmentListBySenderId(Long id);

    Shipment getShipmentByShipmentId(Long id);

    List<Shipment> getShipmentListByReceiverId(Long receiverId);

    List<Shipment> getShipmentListByStatus(Long userId, String status);
}
