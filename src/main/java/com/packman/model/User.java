package com.packman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.packman.model.DataBaseSchemas.AddressSchema;
import com.packman.model.DataBaseSchemas.OriginAddressSchema;
import com.packman.model.DataBaseSchemas.PersonSchema;
import com.packman.model.DataBaseSchemas.UserSchema;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Model Class for User.
 * Created by RushikeshParadkar on 11/15/15.
 */

@Entity
@Table(name = UserSchema.USER_TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @Column(name = UserSchema.USER_ID)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @JsonIgnore
    @Column(name = UserSchema.ACTIVE)
    private boolean active;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @ElementCollection(targetClass = Address.class)
    @JoinTable(name = UserSchema.USER_ADDRESS_TABLE, joinColumns = @JoinColumn(name = UserSchema.USER_ID))
    @GenericGenerator(name = "hilo-gen", strategy = "hilo")
    @CollectionId(columns = {@Column(name = UserSchema.ADDRESS_ID)}, generator = "hilo-gen", type = @Type(type = "long"))
    private Collection<Address> address = new ArrayList<Address>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = PersonSchema.PERSON_ID)
    private Person person;

    @Embedded
    private Address defaultAddress;

    public User() {
    }

    public User(Person person) {
        this.person = person;
    }

    public Collection<Address> getAddress() {
        return address;
    }

    public void setAddress(Collection<Address> address) {
        this.address = address;
    }

    public long getUserId() {
        return userId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Address getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Address defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
