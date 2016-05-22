/*
 *    Copyright 2016 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.simpleapi.api.properties;

import com.exorath.simpleapi.impl.properties.PropertiesImpl;

import java.util.Map;

/**
 * Key value store for temporary data, most likely Map based.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface Properties {

    /**
     * Returns a new instance of the PropertiesImpl. This is done to abstract the implementation layer.
     *
     * @return a new instance of the PropertiesImpl
     */
    static Properties createNewProperties() {
        return new PropertiesImpl();
    }

    /**
     * Returns this properties HashMap with Property keys and object values.
     *
     * @return the storage map of these properties
     */
    Map<Property, Object> asMap();

    /**
     * Gets the value to which the provided property is mapped, or def if property is not found.
     *
     * @param property the property whose associated value is to be returned
     * @param def      this will be returned if no value is mapped to the provided property
     * @return the value to which the property is mapped, or def if key is not found
     */
    Object get(Property property, Object def);

    /**
     * Gets the value to which the provided property is mapped, cast to the provided class, or null if property is not found.
     *
     * @param property the property
     * @param clazz    the type to cast the value to
     * @param <T>      the type
     * @return the value to which the provided property is mapped, cast to the provided class, or null if property is not found
     */
    <T> T as(Property property, Class<T> clazz);

    /**
     * Gets the value to which the provided property is mapped, cast to the provided class, or def if property is not found.
     *
     * @param property the property
     * @param clazz    the type to cast the value to
     * @param def      default to return if provided property has not been mapped
     * @param <T>      the type
     * @return the value to which the provided property is mapped, cast to the provided class, or def if property is not found
     */
    <T> T as(Property property, Class<T> clazz, T def);

    /**
     * Maps the provided value to the provided property
     *
     * @param property the property to map value to
     * @param value    the value you want to map to the property
     */
    void set(Property property, Object value);

    /**
     * Returns true if this map contains a mapping for the specified property.
     *
     * @param property the property whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified property
     */
    boolean has(Property property);


}
