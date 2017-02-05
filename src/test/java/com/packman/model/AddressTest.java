package com.packman.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Address test class to Test {@link Address}
 * Created by rushikeshparadkar on 11/17/15.
 */
public class AddressTest {

    private String teststreet1;
    private String teststreet2;
    private String testlandmark;
    private String testcity;
    private String teststate;
    private String testpinCode;
    private String testzoneId;
    private String testcountry;
    private Address exampleAddress;

    @Before
    public void setup() {
        teststreet1 = "street1";
        teststreet2 = "street2";
        testlandmark = "landmark";
        testcity = "city";
        testcountry = "country";
        teststate = "state";
        testpinCode = "pincode";
        testzoneId = "zoneId";
        exampleAddress = new Address();
        exampleAddress.setCity("city");
        exampleAddress.setCountry("country");
        exampleAddress.setState("state");
        exampleAddress.setLandmark("landmark");
        exampleAddress.setStreet1("street1");
        exampleAddress.setStreet2("street2");
        exampleAddress.setPinCode("pincode");
        exampleAddress.setZoneId("zoneId");
    }

    /**
     * Method to test the Getter {@link Address#getStreet1()}
     */
    @Test
    public void test_getStreet1() {
        assertEquals(teststreet1, exampleAddress.getStreet1());
    }

    /**
     * Method to test the Getter {@link Address#getStreet2()}
     */
    @Test
    public void test_getStreet2() {
        assertEquals(teststreet2, exampleAddress.getStreet2());
    }

    /**
     * Method to test the Getter {@link Address#getLandmark()}
     */
    @Test
    public void test_getLandmark() {
        assertEquals(testlandmark, exampleAddress.getLandmark());
    }

    /**
     * Method to test the Getter {@link Address#getPinCode()}
     */
    @Test
    public void test_getPincode() {
        assertEquals(testpinCode, exampleAddress.getPinCode());
    }

    /**
     * Method to test the Getter {@link Address#getCity()}
     */
    @Test
    public void test_getCity() {
        assertEquals(testcity, exampleAddress.getCity());
    }

    /**
     * Method to test the Getter {@link Address#getState()}
     */
    @Test
    public void test_getState() {
        assertEquals(teststate, exampleAddress.getState());
    }

    /**
     * Method to test the Getter {@link Address#getCountry()}
     */
    @Test
    public void test_getCountry() {
        assertEquals(testcountry, exampleAddress.getCountry());
    }

    /**
     * Method to test the Getter {@link Address#getZoneId()}
     */
    @Test
    public void test_getZoneId() {
        assertEquals(testzoneId, exampleAddress.getZoneId());
    }
}
