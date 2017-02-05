package com.packman.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class to test {@link Person}
 * Created by rushikeshparadkar on 11/17/15.
 */
public class PersonTest {

    private String testFirstName;
    private String testLastName;
    private String testEmail;
    private String testPhone;
    private Person examplePerson;

    /**
     * Method to setup test values for person object.
     */
    @Before
    public void setup() {
        examplePerson = new Person();
        examplePerson.setFirstName("John");
        examplePerson.setLastName("Doe");
        examplePerson.setEmail("exampleEmail@gmail.com");
        examplePerson.setPhone("1234567890");
    }

    /**
     * Method to test the Getter {@link Person#getFirstName()}.
     */
    @Test
    public void test_getFirstName() {
        testFirstName = "John";
        assertEquals(testFirstName, examplePerson.getFirstName());
    }

    /**
     * Method to test the Getter {@link Person#getLastName()}.
     */
    @Test
    public void test_getLastName() {
        testLastName = "Doe";
        assertEquals(testLastName, examplePerson.getLastName());
    }

    /**
     * Method to test the Getter {@link Person#getEmail()}.
     */
    @Test
    public void test_getEmail() {
        testEmail = "exampleEmail@gmail.com";
        assertEquals(testEmail, examplePerson.getEmail());
    }

    /**
     * Method to test the Getter {@link Person#getPhone()}.
     */
    @Test
    public void test_getPhone() {
        testPhone = "1234567890";
        assertEquals(testPhone, examplePerson.getPhone());
    }
}
