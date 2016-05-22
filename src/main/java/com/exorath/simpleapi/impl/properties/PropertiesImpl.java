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

package com.exorath.simpleapi.impl.properties;

import com.exorath.simpleapi.api.properties.Properties;
import com.exorath.simpleapi.api.properties.Property;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public class PropertiesImpl implements Properties{

    private final Map<Property, Object> properties = new HashMap<>();

    public PropertiesImpl() {}

    public PropertiesImpl(Map<Property, Object> properties) {
        this.properties.putAll(properties);
    }

    @Override
    public Map<Property, Object> asMap() {
        return properties;
    }

    @Override
    public Object get(Property property, Object def) {
        return properties.getOrDefault(property, def);
    }

    @Override
    public <T> T as(Property property, Class<T> clazz) {
        return as(property, clazz, (T) property.getDefault());
    }

    @Override
    public <T> T as(Property property, Class<T> clazz, T def) {
        Object object = get(property, def);
        return (T) object;
    }

    @Override
    public void set(Property property, Object value) {
        //TODO: Handle strict properties
        if (property == null || value == null)
            return;
        properties.put(property, value);
    }

    @Override
    public boolean has(Property property) {
        return properties.containsKey(property);
    }
}
