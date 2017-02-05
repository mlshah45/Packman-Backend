package com.packman.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.packman.model.DataBaseSchemas.AgentSchema;
import com.packman.model.DataBaseSchemas.PersonSchema;
import com.packman.model.DataBaseSchemas.ShipmentSchema;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Model Class for Agent.
 * Created by sujaysudheendra on 11/16/15.
 */
@Entity
@Table(name = AgentSchema.AGENT_TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jsonId")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = AgentSchema.AGENT_ID)
    private long agentId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = PersonSchema.PERSON_ID)
    private Person person;

    @Embedded
    private Address address;

    @JsonIgnore
    @Column(name = AgentSchema.ACTIVE)
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = AgentSchema.PICK_UP_TABLE, joinColumns = @JoinColumn(name = AgentSchema.AGENT_ID),
            inverseJoinColumns = @JoinColumn(name = ShipmentSchema.SHIPMENT_ID))
    private Collection<Shipment> assignedShipments = new ArrayList<Shipment>();

    public Agent(Person person, Address address) {
        this.person = person;
        this.address = address;
    }

    public Agent() {
    }

    public Collection<Shipment> getAssignedShipments() {
        return assignedShipments;
    }

    public void setAssignedShipments(Collection<Shipment> assignedShipment) {
        this.assignedShipments = assignedShipment;
    }

    public long getAgentId() {
        return agentId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
}
