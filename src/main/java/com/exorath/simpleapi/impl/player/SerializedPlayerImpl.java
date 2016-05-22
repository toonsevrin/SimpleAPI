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

package com.exorath.simpleapi.impl.player;

import com.exorath.simpleapi.api.player.SerializedPlayer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.UUID;

/**
 * JSON implementation of SerializedPlayer
 * Created by Toon Sevrin on 5/17/2016.
 */
public class SerializedPlayerImpl implements SerializedPlayer {
    private String name;
    private UUID uniqueId;

    public SerializedPlayerImpl(String name, UUID uniqueId){
        this.name = name;
        this.uniqueId = uniqueId;
    }

    @Override
    public String serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("name", name);
        object.addProperty("uuid", uniqueId.toString());
        return object.toString();
    }

    public static SerializedPlayer deserialize(String message){
        JsonObject obj = new JsonParser().parse(message).getAsJsonObject();
        return new SerializedPlayerImpl(obj.get("name").getAsString(), UUID.fromString(obj.get("uuid").getAsString()));
    }


    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public String getName() {
        return name;
    }
}
