package com.packman.Util.notificationManager;

/**
 * Created by mlshah on 4/5/16.
 */
public class PushyPushRequest
{
    public Object data;
    public String[] registration_ids;

    public PushyPushRequest(Object data, String[] registrationIDs)
    {
        this.data = data;
        this.registration_ids = registrationIDs;
    }
}