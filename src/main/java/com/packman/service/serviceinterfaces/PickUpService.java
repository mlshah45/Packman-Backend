package com.packman.service.serviceinterfaces;

/**
 * Created by RushikeshParadkar on 11/18/15.
 */
public interface PickUpService {

    Long schedulePickup(Long userId, Long shipmentId);

    Long cancelPickup(Long shipmentId);

    Long reschedulePickup(Long userId, Long shipmentId, String pickupTime);
}
