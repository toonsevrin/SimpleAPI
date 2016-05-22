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

package com.exorath.simpleapi.impl.hub.serverlist;

import com.exorath.simpleapi.api.hub.serverlist.GameServer;
import com.exorath.simpleapi.api.player.SerializedPlayer;
import com.exorath.simpleapi.impl.player.SerializedPlayerImpl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.*;

/**
 * JSON GameServer implementation:
 * {
 *   "bungee": "<Bungeecord name>",
 *   "joinable": "<joinable>",
 *   "playable": "<playable>",
 *   "maxplayers": "<maximum amount of players>",
 *   "players": [<array of all players according to SerializedPlayerImpl>],
 *   "extra": [<extralore>],
 * }
 * Created by Toon Sevrin on 5/17/2016.
 */
public class GameServerImpl implements GameServer{
    private String bungeeName;
    private boolean joinable = false;
    private boolean playable = false;
    private int maxPlayerAmount = 0;
    private Set<SerializedPlayer> players = new HashSet<>();
    private List<String> extraLore = new ArrayList<>();

    public GameServerImpl(String bungeeName){
        this.bungeeName = bungeeName;
    }

    public GameServerImpl(String bungeeName, boolean joinable, boolean playable, int maxPlayerAmount){
        this(bungeeName);
        this.joinable = joinable;
        this.playable = playable;
        this.maxPlayerAmount = maxPlayerAmount;
    }
    public static boolean canDeserialize(JsonObject object){
        return object.has("bungee");
    }
    public static GameServer deserialize(JsonObject object){
        GameServerImpl gameServer = new GameServerImpl(object.get("bungee").getAsString());

        if(object.has("joinable"))
            gameServer.setJoinable(object.get("joinable").getAsBoolean());
        if(object.has("playable"))
            gameServer.setPlayable(object.get("playable").getAsBoolean());
        if(object.has("maxplayers"))
            gameServer.setMaxPlayerAmount(object.get("maxplayers").getAsInt());

        JsonArray players = object.has("players") ? object.get("players").getAsJsonArray() : new JsonArray();
        players.forEach(e -> gameServer.getPlayers().add(SerializedPlayerImpl.deserialize(e.getAsJsonObject().toString())));
        JsonArray extraLore = object.has("extra") ? object.get("extra").getAsJsonArray() : new JsonArray();
        extraLore.forEach(e -> gameServer.getExtraLore().add(e.getAsString()));

        return gameServer;
    }
    public JsonObject serializeToJson(){
        JsonObject object = new JsonObject();

        object.addProperty("bungee", getBungeeName());
        object.addProperty("joinable", isJoinable());
        object.addProperty("playable", isPlayable());
        object.addProperty("maxplayers", getPlayerAmount());

        JsonArray playersArray = new JsonArray();
        players.forEach(p -> playersArray.add(new JsonParser().parse(p.serialize())));
        JsonArray extraLoreArray = new JsonArray();
        extraLore.forEach(line -> extraLoreArray.add(new JsonPrimitive(line)));
        return object;
    }
    @Override
    public String serialize() {
        return serializeToJson().toString();
    }

    public void setJoinable(boolean joinable) {
        this.joinable = joinable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public void setMaxPlayerAmount(int maxPlayerAmount) {
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
    public boolean isPlayable() {
        return playable;
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

    @Override
    public List<String> getExtraLore() {
        return getExtraLore();
    }
}
