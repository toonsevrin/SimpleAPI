package com.exorath.simpleapi.api.properties;

import java.util.Map;

/**
 * Key value store for temporary data, most likely Map based.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface Properties {
    /**
     * Returns this properties HashMap with Property keys and object values.
     * @return the storage map of these properties
     */
    Map<Property, Object> asMap();

    /**
     * Gets the value to which the provided property is mapped, or def if property is not found.
     * @param property the property whose associated value is to be returned
     * @param def this will be returned if no value is mapped to the provided property
     * @return the value to which the property is mapped, or def if key is not found
     */
    Object get(Property property, Object def);

    /**
     * Gets the value to which the provided property is mapped, cast to the provided class, or null if property is not found.
     *
     * @param property the property
     * @param clazz the type to cast the value to
     * @param <T> the type
     * @return the value to which the provided property is mapped, cast to the provided class, or null if property is not found
     */
    <T> T as(Property property, Class<T> clazz);

    /**
     * Maps the provided value to the provided property
     *
     * @param property the property to map value to
     * @param value the value you want to map to the property
     */
    void set(Property property, Object value);

    /**
     * Returns true if this map contains a mapping for the specified property.
     *
     * @param property the property whose presence in this map is to be tested
     *
     * @return true if this map contains a mapping for the specified property
     */
    boolean has(Property property);

}
