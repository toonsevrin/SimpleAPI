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

package com.exorath.simpleapi.impl.hub;

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
    private boolean joinable;
    private boolean playable;
    private int maxPlayerAmount;
    private Set<SerializedPlayer> players = new HashSet<>();
    private List<String> extraLore = new ArrayList<>();

    public GameServerImpl(String bungeeName, boolean joinable, boolean playable, int maxPlayerAmount, int playerAmount){
        this.bungeeName = bungeeName;
        this.joinable = joinable;
        this.playable = playable;
        this.maxPlayerAmount = maxPlayerAmount;
    }
    public static GameServer deserialize(String message){
        JsonObject object = new JsonParser().parse(message).getAsJsonObject();

        JsonArray players = object.has("players") ? object.get("players").getAsJsonArray() : new JsonArray();
        JsonArray extraLore = object.has("extra") ? object.get("extra").getAsJsonArray() : new JsonArray();

        GameServer gameServer = new GameServerImpl(object.get("bungee").getAsString(), object.get("joinable").getAsBoolean(), object.get("playable").getAsBoolean(), object.get("maxplayers").getAsInt(), players.size());
        players.forEach(e -> gameServer.getPlayers().add(SerializedPlayerImpl.deserialize(e.getAsJsonObject().toString())));
        extraLore.forEach(e -> gameServer.getExtraLore().add(e.getAsString()));

        return gameServer;
    }

    @Override
    public String serialize() {
        JsonObject object = new JsonObject();

        object.addProperty("bungee", getBungeeName());
        object.addProperty("joinable", isJoinable());
        object.addProperty("playable", isPlayable());
        object.addProperty("maxplayers", getPlayerAmount());

        JsonArray playersArray = new JsonArray();
        players.forEach(p -> playersArray.add(new JsonParser().parse(p.serialize())));
        JsonArray extraLoreArray = new JsonArray();
        extraLore.forEach(line -> extraLoreArray.add(new JsonPrimitive(line)));

        return object.toString();
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
