package com.packman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.packman.model.DataBaseSchemas.AddressSchema;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Model Class for Address
 * Created by mlshah on 11/15/2015.
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    @Column(name = AddressSchema.STREET_ONE)
    private String street1;
    @Column(name = AddressSchema.STREET_TWO)
    private String street2;
    @Column(name = AddressSchema.LANDMARK)
    private String landmark;
    @Column(name = AddressSchema.CITY)
    private String city;
    @Column(name = AddressSchema.STATE)
    private String state;
    @Column(name = AddressSchema.PIN_CODE)
    private String pinCode;
    @Column(name = AddressSchema.ZONE_ID)
    private String zoneId;
    @Column(name = AddressSchema.COUNTRY)
    private String country;

    public Address() {
    }

    public Address(String street1, String street2, String landmark, String city, String state,
                   String zoneId, String pinCode, String country) {
        this.street1 = street1;
        this.street2 = street2;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.zoneId = zoneId;
        this.pinCode = pinCode;
        this.country = country;
    }

    @Column
    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    @Column
    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    @Column
    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @Column
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column
    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Column
    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    @Column
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
