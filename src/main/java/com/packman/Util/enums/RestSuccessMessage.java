package com.packman.Util.enums;

/**
 * Created by sujaysudheendra on 5/4/16.
 */
public enum RestSuccessMessage {
    DATABASE(820, "Database has been updated."),
    REQUEST_PROCESS_SUCCESS(821, "The request is successfully processed."),
    AGENT_FOUND(822, "Agent was found for this id"),
    AGENT_UPDATED(823, "updated the agent details"),
    AGENT_DELETED(824, "Deleted the agent"),
    AGENT_SHIPMENT_BY_STATUS_RETURNED(825, "Successfully found agent shipments by provided status"),
    AGENT_RESPONSE(825, "Agent response returned"),
    USER_FOUND(826, "User found for the given user id"),
    USER_UPDATED(827, "User updated successfully"),
    USER_DELETED(828, "Deleted user successfully"),
    GET_ALL_USER(829, "Get all the users"),
    SHIPMENT_FOUND(830, "Shipment found for the given shipment id"),
    DELETE_SHIPMENT(831, "Deleted shipment successfully"),
    UPDATE_SHIPMENT(832, "Shipment Updated successfully"),
    SAVE_SHIPMENT(833, "Shipment saved successfully"),
    SHIPMENT_BY_SENDER(834, "Shipments for the sender id provided"),
    SHIPMENT_BY_RECEIVER(835, "Shipments for the receiver id provided"),
    SHIPMENT_BY_STATUS(836, "Shipments for the STATUS provided"),
    REQUEST_PICKUP(837, "Request to schedule pickup placed successfully"),
    CANCEL_PICKUP(838, "Request to cancel pickup placed successfully"),
    RESCHEDULE_PICKUP(839, "Request to reschedule pickup placed successfully"),
    ADD_USER(840, "New user added successfully");


    //processing request

    private final int code;
    private final String message;

    private RestSuccessMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + message;
    }
}
