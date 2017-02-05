package com.packman.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.packman.model.DataBaseSchemas.BoxSchema;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by sujaysudheendra on 11/16/15.
 */
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Box {
    @Column(name = BoxSchema.WEIGHT)
    private double weight;
    @Column(name = BoxSchema.HEIGHT)
    private double height;
    @Column(name = BoxSchema.LENGTH)
    private double length;
    @Column(name = BoxSchema.WIDTH)
    private double width;
    @Column(name = BoxSchema.IMAGE, length = BoxSchema.MAX_SIZE)
    private byte[] image;

    public Box(double weight, double height, double length, double width) {
        this.weight = weight;
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public Box(byte[] image, double width, double length, double height, double weight) {
        this.image = image;
        this.width = width;
        this.length = length;
        this.height = height;
        this.weight = weight;
    }

    public Box() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
