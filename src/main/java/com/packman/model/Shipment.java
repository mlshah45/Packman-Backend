package com.packman.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.packman.Util.JsonDateDeserializer;
import com.packman.Util.JsonDateSerializer;
import com.packman.Util.enums.ShipmentStatus;
import com.packman.model.DataBaseSchemas.DestinationAddressSchema;
import com.packman.model.DataBaseSchemas.OriginAddressSchema;
import com.packman.model.DataBaseSchemas.ShipmentSchema;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;


/**
 * Model Class for Shipment.
 * Created by sujaysudheendra on 11/16/15.
 */
@Entity
@Table(name = ShipmentSchema.TABLE_NAME)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@jsonShipmentId")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ShipmentSchema.SHIPMENT_ID)
    private long shipmentId;
    @OneToOne(fetch = FetchType.EAGER)
    private User sender;
    @OneToOne(fetch = FetchType.EAGER)
    private User receiver;
    @Column(name = ShipmentSchema.RECEIVER_NAME)
    private String receiverName;
    @Column(name = ShipmentSchema.SHIPMENT_PRICE)
    private BigDecimal price;
    @Column(name = ShipmentSchema.CURRENCY_TYPE)
    private String currency;
    @Column(name = ShipmentSchema.TRACKING_ID)
    private long trackingId;
    @Column(name = ShipmentSchema.SHIPMENT_STATUS)
    private String status;
    @Embedded
    private Box box;

    @Column(name = ShipmentSchema.PICKUP_TIME)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private LocalDateTime pickupTime;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "assignedShipments", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Agent> agentList = new ArrayList<Agent>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street1", column = @Column(name = DestinationAddressSchema.STREET_ONE)),
            @AttributeOverride(name = "street2", column = @Column(name = DestinationAddressSchema.STREET_TWO)),
            @AttributeOverride(name = "landmark", column = @Column(name = DestinationAddressSchema.LANDMARK)),
            @AttributeOverride(name = "city", column = @Column(name = DestinationAddressSchema.CITY)),
            @AttributeOverride(name = "zoneId", column = @Column(name = DestinationAddressSchema.ZONE_ID)),
            @AttributeOverride(name = "state", column = @Column(name = DestinationAddressSchema.STATE)),
            @AttributeOverride(name = "pinCode", column = @Column(name = DestinationAddressSchema.PIN_CODE)),
            @AttributeOverride(name = "country", column = @Column(name = DestinationAddressSchema.COUNTRY))
    })
    private Address toAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street1", column = @Column(name = OriginAddressSchema.STREET_ONE)),
            @AttributeOverride(name = "street2", column = @Column(name = OriginAddressSchema.STREET_TWO)),
            @AttributeOverride(name = "landmark", column = @Column(name = OriginAddressSchema.LANDMARK)),
            @AttributeOverride(name = "city", column = @Column(name = OriginAddressSchema.CITY)),
            @AttributeOverride(name = "state", column = @Column(name = OriginAddressSchema.STATE)),
            @AttributeOverride(name = "zoneId", column = @Column(name = OriginAddressSchema.ZONE_ID)),
            @AttributeOverride(name = "pinCode", column = @Column(name = OriginAddressSchema.PIN_CODE)),
            @AttributeOverride(name = "country", column = @Column(name = OriginAddressSchema.COUNTRY))
    })
    private Address fromAddress;


    public Shipment(User sender, User receiver, Address toAddress, Address fromAddress, BigDecimal price,
                    String currency, ShipmentStatus status, LocalDateTime pickupTime, Box box) {
        this.sender = sender;
        this.receiver = receiver;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.price = price;
        this.currency = currency;
        this.status = status.getStatus();
        this.pickupTime = pickupTime;
        this.box = box;
    }

    public Shipment(User sender, Address toAddress, Address fromAddress, BigDecimal price,
                    String currency, ShipmentStatus status, LocalDateTime pickupTime, Box box) {
        this.sender = sender;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.price = price;
        this.currency = currency;
        this.status = status.getStatus();
        this.pickupTime = pickupTime;
        this.box = box;
    }

    public Shipment(User sender, String receiverName, Address toAddress, Address fromAddress, BigDecimal price,
                    String currency, ShipmentStatus status, LocalDateTime pickupTime, Box box) {
        this.sender = sender;
        this.receiverName = receiverName;
        this.toAddress = toAddress;
        this.fromAddress = fromAddress;
        this.price = price;
        this.currency = currency;
        this.status = status.getStatus();
        this.pickupTime = pickupTime;
        this.box = box;
    }

    public Shipment() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return Currency.getInstance(currency);
    }

    public void setCurrency(Currency currency) {
        this.currency = currency.getCurrencyCode();
    }

    public void setTrackingId(long trackingId) {
        this.trackingId = trackingId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status.getStatus();
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    public long getShipmentId() {
        return shipmentId;
    }


    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Collection<Agent> getAgentList() {
        return agentList;
    }

    public void setAgentList(Collection<Agent> agent) {
        this.agentList = agent;
    }
}
