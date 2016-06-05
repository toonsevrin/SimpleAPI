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

package com.exorath.simpleapi.api.hub.serverlist;

import com.exorath.simpleapi.api.serverlist.GameServer;
import com.exorath.simpleapi.api.serverlist.Server;
import com.exorath.simpleapi.api.serverlist.ServerListManager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This event is called just before a GameServer is added to the hubs ServerListManager. Cancelling this event will prevent the GameServer from being added to the {@link ServerListManager}.
 * Created by Toon Sevrin on 5/22/2016.
 */
public class ServerAddedEvent extends Event implements Cancellable, GameServerEvent {
    private static final HandlerList handlerList = new HandlerList();
    private Server server;
    private int expireAfter;
    private boolean cancelled = false;

    public ServerAddedEvent(Server server, int expireAfter){
        this.server = server;
        this.expireAfter = expireAfter;
    }

    public int getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(int expireAfter) {
        this.expireAfter = expireAfter;
    }

    @Override
    public Server getGameServer() {
        return server;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
