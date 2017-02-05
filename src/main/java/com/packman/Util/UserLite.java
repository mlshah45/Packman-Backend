package com.packman.Util;

import com.packman.model.Address;

/**
 * Created by mlshah on 5/8/16.
 */
public class UserLite {

    private Long userId;
    private String firstName;
    private String lastName;
    private Address defaultAddress;

    public UserLite() {}

    public UserLite(Long userId, String firstName, String lastName, Address defaultAddress) {
        this.userId = userId;
        this.firstName = firstName;
        this.defaultAddress = defaultAddress;
        this.lastName = lastName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Address defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
