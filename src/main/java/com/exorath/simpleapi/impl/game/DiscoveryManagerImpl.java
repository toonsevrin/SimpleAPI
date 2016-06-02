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

package com.exorath.simpleapi.impl.game;

import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.game.Game;
import com.exorath.simpleapi.api.game.discovery.DiscoveryManager;
import com.exorath.simpleapi.api.redis.RedisManager;
import com.exorath.simpleapi.impl.SimpleAPIImpl;
import com.exorath.simpleapi.impl.hub.serverlist.GameServerImpl;
import com.exorath.simpleapi.impl.player.SerializedPlayerImpl;
import com.google.gson.JsonObject;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toon Sevrin on 5/22/2016.
 */
public class DiscoveryManagerImpl implements DiscoveryManager{
    private int heartbeat = 20;
    private int expireTime = 60;

    private Game game;

    private List<String> extraLore = new ArrayList<>();

    public DiscoveryManagerImpl(Game game){
        //TODO: Load heartbeat and expireTime from config
        this.game = game;
        startScheduler();
    }

    private void startScheduler(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SimpleAPIImpl.getInstance(), () -> {
            sendGameDiscoveryMessage();
        }, heartbeat, heartbeat);
    }

    @Override
    public List<String> getExtraLore() {
        return extraLore;
    }

    @Override
    public void sendGameDiscoveryMessage(){
        SimpleAPI.getInstance().getManager(RedisManager.class).publish(getChannel(), getGameDiscoveryMessage().toString());
    }
    private String getChannel(){
        return SimpleAPI.getInstance().getGameHubProvider().getID();
    }

    public JsonObject getGameDiscoveryMessage(){
        JsonObject message = new JsonObject();
        message.addProperty("expire", expireTime);
        message.add("gameserver", getGameServer().serializeToJson());
        return message;
    }

    public GameServerImpl getGameServer(){
        Validate.notNull(SimpleAPI.getInstance().getBungeeId(), "Failed to load bungeeId.");

        GameServerImpl gameServer = new GameServerImpl(SimpleAPI.getInstance().getBungeeId(), game.isJoinable(), game.isPlayable(), game.getMaxPlayers());

        game.getPlayers().forEach(gp -> gameServer.getPlayers().add(SerializedPlayerImpl.deserialize(gp)));

        if(extraLore.size() > 0)
            gameServer.getExtraLore().addAll(extraLore);

        return gameServer;
    }
}
