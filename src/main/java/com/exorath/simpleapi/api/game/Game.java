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

package com.exorath.simpleapi.api.game;

import com.exorath.simpleapi.api.GameHubProvider;
import com.exorath.simpleapi.api.PrimaryModule;
import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.player.GamePlayer;
import com.exorath.simpleapi.api.properties.Properties;
import com.exorath.simpleapi.impl.game.DiscoveryManagerImpl;
import com.exorath.simpleapi.impl.properties.PropertiesImpl;
import org.bukkit.Bukkit;

import java.util.Collection;

/**
 * The API can register up to one Game. It must be registered through a {@link GameHubProvider}.
 * Created by Toon Sevrin on 5/15/2016.
 */
public abstract class Game extends PrimaryModule {
    public static String GAME_SERVERS_PREFIX = "gameservers.";

    private String gameName;
    private Properties properties = new PropertiesImpl();

    public Game(String gameName){
        this.gameName = gameName;
        SimpleAPI.getInstance().addManager(new DiscoveryManagerImpl(this));
    }

    /**
     * Gets the redis channel where games of this GameHubProvider publish their status to
     * @return the redis channel where games of this GameHubProvider publish their status to, or null if no GameHubProvider registered.
     */
    public static String getRedisGameDiscoveryChannel(){
        return SimpleAPI.getInstance().getGameHubProvider() == null ? null : GAME_SERVERS_PREFIX + SimpleAPI.getInstance().getGameHubProvider().getID();
    }

    /**
     * Gets the {@link Properties} corresponding to this Game.
     *
     * @return The properties.
     */
    public Properties getProperties(){
        return properties;
    }

    /**
     * True if this game can be joined (Regardless whether or not you will be playing after joining).
     * @return true if this game can be joined
     */
    public boolean isJoinable(){
        return Bukkit.getOnlinePlayers().size() < getMaxPlayers();
    }

    /**
     * True if this game is joinable and joined players will be able to play the game.
     * @return true if this game is joinable and joined players will be able to play the game
     */
    public boolean isPlayable(){
        return isJoinable();
    }

    /**
     * Gets the maximum amount of players allowed to play on this server. By default this calls {@link Bukkit#getMaxPlayers()}.
     * @return allowed to play on this server
     */
    public int getMaxPlayers(){
        return Bukkit.getMaxPlayers();
    }

    /**
     * Gets the players playing on this server (Not spectating).
     * @return the players playing on this server (Not spectating)
     */
    public abstract Collection<GamePlayer> getPlayers();
    /**
     * Gets this Game's name. This may be used for display purposes.
     *
     * @return the name
     */
    public String getName(){
        return gameName;
    }

    /**
     * Sets this Game's name. This may be used for display purposes.
     *
     * @param gameName the new name
     */
    public void setName(String gameName){
        this.gameName = gameName;
    }
}
