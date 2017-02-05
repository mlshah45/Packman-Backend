package com.packman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.packman.model.DataBaseSchemas.CredentialsSchema;
import com.packman.model.DataBaseSchemas.DeviceRegisterationSchema;
import com.packman.model.DataBaseSchemas.PersonSchema;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Model Class for Person.
 *
 * Created by rushikeshparadkar on 11/15/15.
 */
@Entity
@Table(name = PersonSchema.PERSON_TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = PersonSchema.PERSON_ID)
    private long personId;

    @Column(name = PersonSchema.FIRST_NAME)
    private String firstName;
    @Column(name = PersonSchema.LAST_NAME)
    private String lastName;
    @Column(name = PersonSchema.EMAIL)
    private String email;
    @Column(name = PersonSchema.PHONE)
    private String phone;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = CredentialsSchema.CREDENTIAL_ID, nullable = true)
    private Credentials credentials;
    @Column(name = PersonSchema.SOCIAL_ID)
    private String socialId;

    @Column(name = PersonSchema.PROFILE_PIC_URL)
    private String profilePicUrl;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @ElementCollection(targetClass = DeviceRegisteration.class)
    @JoinTable(name = PersonSchema.PERSON_DEVICE_REGISTERATION_TABLE, joinColumns = @JoinColumn(name = PersonSchema.PERSON_ID))
    @GenericGenerator(name = "hilo-gen", strategy = "hilo")
    @CollectionId(columns = {@Column(name = DeviceRegisterationSchema.NOTIFICATION_ID)}, generator = "hilo-gen", type = @Type(type = "long"))
    private Collection<DeviceRegisteration> deviceRegisteration = new ArrayList<DeviceRegisteration>();

    public Person() {
    }

    public Person(String lastName, String firstName, String email, String profilePicUrl, String socialId) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.socialId = socialId;
        this.profilePicUrl = profilePicUrl;
    }

    public Person(String lastName, String firstName, String email, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "person")
    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getPersonId() {
        return personId;
    }

    public Collection<DeviceRegisteration> getDeviceRegisteration() {
        return deviceRegisteration;
    }

    public void setDeviceRegisteration(Collection<DeviceRegisteration> deviceRegisteration) {
        this.deviceRegisteration = deviceRegisteration;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

}
