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

import com.exorath.simpleapi.api.properties.Property;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Toon Sevrin on 5/17/2016.
 */
public class PropertyImpl implements Property {
    private static Map<String, Property> properties = new HashMap<String, Property>();

    private final String key;
    private final Object def;

    public PropertyImpl(String key, Object def){
        this.key = key;
        this.def = def;

        if (key != null)
            PropertyImpl.getProperties().put(key.toLowerCase(), this);
    }

    public static Map<String, Property> getProperties(){
        return properties;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getDefault() {
        return def;
    }
}
