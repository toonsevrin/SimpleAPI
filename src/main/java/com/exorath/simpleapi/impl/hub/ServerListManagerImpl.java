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

import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.game.minigame.Minigame;
import com.exorath.simpleapi.api.hub.serverlist.GameServer;
import com.exorath.simpleapi.api.hub.serverlist.ServerListManager;
import com.exorath.simpleapi.api.redis.RedisManager;
import com.exorath.simpleapi.api.redis.RedisSubscriber;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Toon Sevrin on 5/17/2016.
 */
public class ServerListManagerImpl extends RedisSubscriber implements ServerListManager {
    private static int TTL = 1;
    private String channel;
    private Set<GameServer> gameServers = new HashSet<>();

    public ServerListManagerImpl(String bungeeId){
        super(Minigame.GAME_SERVERS_PREFIX + bungeeId);
        SimpleAPI.getInstance().getManager(RedisManager.class).getRedisPool().getResource().get("mcservers");
    }
    @Override
    public void onMessage(String channel, String message) {
        if(channel.equals(channel)){
            new GameServer()
        }
    }

    @Override
    public Collection<GameServer> getGameServers() {
        return gameServers;
    }
}
