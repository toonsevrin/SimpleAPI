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

package com.exorath.simpleapi.impl.hub.discovery.serverlist;

import com.exorath.simpleapi.api.serverlist.HubServer;
import com.exorath.simpleapi.api.player.SerializedPlayer;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Toon Sevrin on 6/2/2016.
 */
public class HubServerImpl implements HubServer {
    private String bungeeName;
    private boolean joinable = false;
    private int maxPlayerAmount = 0;
    private Set<SerializedPlayer> players = new HashSet<>();


    public HubServerImpl(String bungeeName){
        this.bungeeName = bungeeName;
    }

    public HubServerImpl(String bungeeName, boolean joinable, int maxPlayerAmount){
        this(bungeeName);
        this.joinable = joinable;
        this.maxPlayerAmount = maxPlayerAmount;
    }

    @Override
    public String getBungeeName() {
        return bungeeName;
    }

    @Override
    public boolean isJoinable() {
        return joinable;
    }

    @Override
    public int getMaxPlayerAmount() {
        return maxPlayerAmount;
    }

    @Override
    public int getPlayerAmount() {
        return players.size();
    }

    @Override
    public Collection<SerializedPlayer> getPlayers() {
        return players;
    }

    public JsonObject serializeToJson(){
        JsonObject object = new JsonObject();

        object.addProperty("bungee", getBungeeName());
        object.addProperty("joinable", isJoinable());
        object.addProperty("maxplayers", getPlayerAmount());

        JsonArray playersArray = new JsonArray();
        players.forEach(p -> playersArray.add(new JsonParser().parse(p.serialize())));
        return object;
    }

    @Override
    public String serialize() {
        return serializeToJson().toString();
    }
}
