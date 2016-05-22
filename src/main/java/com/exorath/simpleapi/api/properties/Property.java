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

import com.exorath.simpleapi.impl.properties.PropertyImpl;

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


    /**
     * Gets or creates a Property with the specified key and default value.
     *
     * @param key the key
     * @param def the default value
     *
     * @return the property
     */
    static Property get(String key, Object def) {
        return key != null && PropertyImpl.getProperties().containsKey(key.toLowerCase())
                ? PropertyImpl.getProperties().get(key.toLowerCase())
                : new PropertyImpl(key, def);
    }
}
