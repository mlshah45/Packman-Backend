package com.packman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.packman.model.DataBaseSchemas.DeviceRegisterationSchema;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by mlshah on 4/10/16.
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceRegisteration {

    @Column(name = DeviceRegisterationSchema.REGISTERATION_ID)
    private String registerationId;

    public DeviceRegisteration() {}

    public DeviceRegisteration(String registerationId) {
        this.registerationId = registerationId;
    }

    public String getRegisterationId() {
        return registerationId;
    }

    public void setRegisterationId(String registerationId) {
        this.registerationId = registerationId;
    }
}
