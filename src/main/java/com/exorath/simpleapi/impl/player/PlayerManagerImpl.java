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

import com.exorath.simpleapi.api.player.GamePlayer;
import com.exorath.simpleapi.api.player.PlayerManager;
import com.exorath.simpleapi.impl.SimpleAPIImpl;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public class PlayerManagerImpl implements PlayerManager, Listener{
    private Set<GamePlayer> players = new HashSet<>();

    public PlayerManagerImpl(){
        Bukkit.getPluginManager().registerEvents(this, SimpleAPIImpl.getInstance());
    }

    @EventHandler
    public void onLogin(AsyncPlayerPreLoginEvent event){
        GamePlayer player = new GamePlayerImpl(event.getUniqueId());
        players.add(player);
    }
    @EventHandler
    public void leave(GamePlayer player) {
        players.remove(player);
    }

    @Override
    public Collection<GamePlayer> getPlayers() {
        return players;
    }

}
