package com.packman.exception;

/**
 * Created by mlshah on 12/6/15.
 */
public enum RestErrorMessages {
    DATABASE(0, "A database error has occured."),
    REQUEST_PROCESS_ERROR(1, "Unable to process this request."),
    NO_AGENT_FOUND(2, "No agent found for this id"),
    DUPLICATE_AGENT(3, "This agent already exists"),
    AGENT_UPDATE_FAILED(4, "Failed to update agent"),
    AGENT_DELETE_FAILED(5, "Failed to delete this agent"),
    AGENT_SHIPMENT_BY_STATUS_FAILED(6, "Failed to get agent shipments by provided status"),
    AGENT_RESPONSE_FAILED(7, "Failed to process on agent response"),
    DUPLICATE_USER(8, "This user already exists."),
    NO_USER_FOUND(9, "No User found for this user id"),
    USER_UPDATE_FAILED(10, "Failed to update this user"),
    USER_DELETE_FAILED(11, "Failed to delete this user"),
    GET_ALL_USER_FAILED(12, "Failed to get all the users"),
    NO_SHIPMENT_FOUND(13, "No shipment found for this shipment id"),
    DELETE_SHIPMENT_FAILED(14, "Failed to delete shipment"),
    UPDATE_SHIPMENT_FAILED(15, "Failed to updat shipment"),
    SAVE_SHIPMENT_FAILED(16, "Failed to save shipment"),
    SHIPMENT_BY_SENDER_FAILED(17, "Failed to get shipment by sender id"),
    SHIPMENT_BY_RECEIVER_FAILED(18, "Failed to get shipment by receiver id"),
    SHIPMENT_BY_STATUS_FAILED(19, "Failed to get shipment by status"),
    REQ_PICKUP_FAILED(20, "Failed to request pickup"),
    CANCEL_PICKUP_FAILED(21, "Failed to cancel pickup"),
    RESCHEDULE_PICKUP_FAILED(22, "Failed to reschedule pickup"),
    ADD_USER_FAILED(23, "Failed to add new user");


    //processing request

    private final int code;
    private final String message;

    private RestErrorMessages(int code, String message) {
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
