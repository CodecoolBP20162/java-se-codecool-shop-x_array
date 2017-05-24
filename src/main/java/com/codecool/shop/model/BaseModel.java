package com.codecool.shop.model;

import java.lang.reflect.Field;

/**
 * <h1>Class is used as blueprint for all other model classes</h1>
 * This class is never instantiated in app only used as blueprint.
 *
 * @author Adam Kovacs
 * @author Daniel Majoross
 * @author Anna Racz
 * @version 1.0
 * @since 20-05-2017
 */

public class BaseModel {

    /**
     * For the identification of each instance
     */
    protected int id;
    /**
     * Name of object
     */
    protected String name;
    /**
     * Description of object
     */
    protected String description;

    /**
     * Basic constructor
     * @param name name for the object
     */
    public BaseModel(String name) {
        this.name = name;
    }

    /**
     * Comprehensive constructor
     * @param name name for the object
     * @param description description for the object
     */
    public BaseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Overwrites default toString to return all attributes in a formatted string
     * @return formatted string with attributes of object
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }

}
