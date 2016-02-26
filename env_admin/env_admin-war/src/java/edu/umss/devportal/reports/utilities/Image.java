/*
 * @(#)Image.java 11/04/23
 *
 * Copyright (c) 2011 Universidad Mayor de San Simón
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.utilities;

import org.primefaces.model.StreamedContent;

/**
 * Represents an image that can be iterated inside a list
 *
 * @author Edson Alvarez
 */
public class Image {

    private Integer id;
    private StreamedContent image;
    private String name;

    /**
     * Default constructor
     */
    public Image() {
    }

    /**
     * Constructor with ID and image content as parameters
     * @param id represents the key of the image
     * @param image represents the image
     */
    public Image(Integer id, StreamedContent image) {
        this.id = id;
        this.image = image;
    }

    /**
     * Constructor with ID, image content and name as parameters
     * @param id represents the key of the image
     * @param image represents the image
     * @param name represents the name of the image
     */
    public Image(Integer id, StreamedContent image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    /**
     * Represents the image name
     * @return String that contains the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the image
     * @param name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the ID of the image
     * @return an Integer that represents the ID of the image
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the image
     * @param id that represents the ID of the Image
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the content of the image
     * @return a Streamed Content that represents the image
     */
    public StreamedContent getImage() {
        return image;
    }

    /**
     * Sets the content of the image
     * @param image that contains the data to display the image
     */
    public void setImage(StreamedContent image) {
        this.image = image;
    }
}
