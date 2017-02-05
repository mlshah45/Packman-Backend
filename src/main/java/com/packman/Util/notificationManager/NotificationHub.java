package com.packman.Util.notificationManager;

import com.packman.model.Agent;
import com.packman.model.DeviceRegisteration;
import com.packman.model.User;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by mlshah on 12/1/15.
 * TODO: use the functions in this class to send notification
 */
@Component
public class NotificationHub {
    public static boolean sendNotificationToUsers(List<User> users, String message){
        List<DeviceRegisteration> devices = new ArrayList<>();
        // adding all the devices of all the users
        for(User singleUser: users) {
            devices.addAll(singleUser.getPerson().getDeviceRegisteration());
        }

        // generating a list of all registeration ids of th devices to send notification
        List<String> regIds = new ArrayList<>();
        for(DeviceRegisteration singleDevice : devices) {
            regIds.add(singleDevice.getRegisterationId());
        }
        Map<String, String> payload = new HashMap<String, String>();
        // This will be the message which will be displayed
        payload.put("message", message);
        sendNotification(regIds, payload);
        return true;
    }

    public static boolean sendNotificationToAgents(List<Agent> agents, String message){
        List<DeviceRegisteration> devices = new ArrayList<>();
        // adding all the devices of all the agents
        for(Agent singleAgent: agents) {
            devices.addAll(singleAgent.getPerson().getDeviceRegisteration());
        }

        // generating a list of all registeration ids of th devices to send notification
        List<String> regIds = new ArrayList<>();
        for(DeviceRegisteration singleDevice : devices) {
            regIds.add(singleDevice.getRegisterationId());
        }
        Map<String, String> payload = new HashMap<String, String>();
        // This will be the message which will be displayed
        payload.put("message", message);
        sendNotification(regIds, payload);
        return true;
    }

    /**
     * This method is used to send push notifcations to the devices
     * @param registrationIDs, reg ids of the devices that will receive the push notification
     * @param payload, payload consists of message to show on the devices
     */
    public static void sendNotification(List<String> registrationIDs, Map<String,String> payload) {

        PushyPushRequest push = new PushyPushRequest(payload, registrationIDs.toArray( new String[registrationIDs.size()] ));
        try
        {
            // Try sending the push notification
            PushyAPI.sendPush(push);
        }
        catch( Exception exc )
        {
            // Error, print to output
            //TODO: handle error
        }
    }
}
