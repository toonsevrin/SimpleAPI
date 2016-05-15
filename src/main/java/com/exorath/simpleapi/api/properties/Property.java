package com.exorath.simpleapi.api.properties;

/**
 * A property represents the key for the {@link Properties} map.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface Property {
    /**
     * Returns the identifier of this property.
     * @return the identifier of this property
     */
    String getKey();


    /**
     * Returns the default value of this property.
     * @return the default value of this property
     */
    Object getDefault();
}
