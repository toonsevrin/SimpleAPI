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

package com.exorath.simpleapi.api.database;

import java.util.Date;

/**
 * Key value based database storage (Most likely MongoDB in backend).
 * Keys can contain dots ('.') to dig down nodes.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface DBData {

    /**
     * Associates the specified value with the specified key. Remote database is updated asynchronously to this method.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    void put(String key, Object value);

    /**
     * Gets the value, cast to specified class, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key   the key whose associated value is to be returned
     * @param clazz class value should be returned as
     * @param <T>   class value should be returned as
     * @return the value, cast to specified class, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    <T> T as(String key, Class<T> clazz);

    /**
     * Gets the value, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Object get(String key);

    /**
     * Gets the value, to which the specified key is mapped, or def if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, to which the specified key is mapped, or def if this map contains no mapping for the key
     */
    Object get(String key, Object def);

    /**
     * Gets the value to which the specified key is mapped, or provided default if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @param def default value to return if no mapping is found for the key
     * @return the String value to which the specified key is mapped, or provided default if this map contains no mapping for the key
     */
    String getString(String key, String def);

    /**
     * Gets the value, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    String getString(String key);

    /**
     * Gets the value to which the specified key is mapped, or provided default if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @param def default value to return if no mapping is found for the key
     * @return the String value to which the specified key is mapped, or provided default if this map contains no mapping for the key
     */
    Integer getInt(String key, Integer def);

    /**
     * Gets the value, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Integer getInt(String key);

    /**
     * Gets the value to which the specified key is mapped, or provided default if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @param def default value to return if no mapping is found for the key
     * @return the String value to which the specified key is mapped, or provided default if this map contains no mapping for the key
     */
    Boolean getBoolean(String key, Boolean def);

    /**
     * Gets the value, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Boolean getBoolean(String key);

    /**
     * Gets the value to which the specified key is mapped, or provided default if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @param def default value to return if no mapping is found for the key
     * @return the String value to which the specified key is mapped, or provided default if this map contains no mapping for the key
     */
    Long getLong(String key, Long def);

    /**
     * Gets the value, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Long getLong(String key);

    /**
     * Gets the value to which the specified key is mapped, or provided default if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @param def default value to return if no mapping is found for the key
     * @return the String value to which the specified key is mapped, or provided default if this map contains no mapping for the key
     */
    Double getDouble(String key, Double def);

    /**
     * Gets the value, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Double getDouble(String key);

    /**
     * Gets the value to which the specified key is mapped, or provided default if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @param def default value to return if no mapping is found for the key
     * @return the String value to which the specified key is mapped, or provided default if this map contains no mapping for the key
     */
    Date getDate(String key, Date def);

    /**
     * Gets the value, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Date getDate(String key);

    /**
     * Increments integer (or created with provided amount if non-existent) mapped to key with provided amount.
     *
     * @param key    the key whose associated value is to be incremented
     * @param amount amount the integer will be incremented with
     */
    void incrementInt(String key, int amount);

    /**
     * Increments double (or created with provided amount if non-existent) mapped to key with provided amount.
     *
     * @param key    the key whose associated value is to be incremented
     * @param amount amount the double will be incremented with
     */
    void incrementDouble(String key, double amount);

    /**
     * Removes the mapping for the specified key from this map if present
     *
     * @param key whose mapping is to be removed from the map
     */
    void remove(String key);

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key The key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    boolean contains(String key);


}
