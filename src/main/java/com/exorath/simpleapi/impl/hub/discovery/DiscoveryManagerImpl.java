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

package com.exorath.simpleapi.impl.hub.discovery;

import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.hub.Hub;
import com.exorath.simpleapi.api.hub.discovery.DiscoveryManager;
import com.exorath.simpleapi.api.redis.RedisManager;
import com.exorath.simpleapi.impl.SimpleAPIImpl;
import com.exorath.simpleapi.impl.hub.discovery.serverlist.HubServerImpl;
import com.exorath.simpleapi.impl.player.SerializedPlayerImpl;
import com.google.gson.JsonObject;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;

/**
 * Created by Toon Sevrin on 6/2/2016.
 */
public class DiscoveryManagerImpl implements DiscoveryManager {
    private int heartbeat = 20;
    private int expireTime = 60;

    private Hub hub;

    public DiscoveryManagerImpl(Hub hub) {
        //TODO: Load heartbeat and expireTime from config
        this.hub = hub;
        startScheduler();
    }

    private void startScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SimpleAPIImpl.getInstance(), () -> {
            sendHubDiscoveryMessage();
        }, heartbeat, heartbeat);
    }

    @Override
    public void sendHubDiscoveryMessage() {
        SimpleAPI.getInstance().getManager(RedisManager.class).publish(SimpleAPI.getInstance().getGameHubProvider().getID(), getHubDiscoveryMessage().toString());
    }
    public JsonObject getHubDiscoveryMessage(){
        JsonObject message = new JsonObject();
        message.addProperty("expire", expireTime);
        message.add("hubserver", getHubServer().serializeToJson());
        return message;
    }

    public HubServerImpl getHubServer(){
        Validate.notNull(SimpleAPI.getInstance().getBungeeId(), "Failed to load bungeeId.");

        HubServerImpl hubServer = new HubServerImpl(SimpleAPI.getInstance().getBungeeId(), hub.isJoinable(), hub.getMaxPlayers());
        hub.getPlayers().forEach(gp -> hubServer.getPlayers().add(SerializedPlayerImpl.deserialize(gp)));

        return hubServer;
    }
}
