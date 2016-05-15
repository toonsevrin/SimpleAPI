package com.exorath.simpleapi.api.database;

import java.util.Date;

/**
 * Key value based database storage (Most likely MongoDB in backend).
 * Keys can contain dots ('.') to dig down nodes.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface DBData {
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
     * Gets the value, cast to specified class, to which the specified key is mapped, or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value, cast to specified class, to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    Object get(String key);

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
