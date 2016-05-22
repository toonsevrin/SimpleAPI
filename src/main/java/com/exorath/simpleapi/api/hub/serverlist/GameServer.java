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

import com.exorath.simpleapi.api.player.SerializedPlayer;

import java.util.Collection;
import java.util.List;

/**
 * The GameServer is created from redis pings send from game servers to the hub servers. They are used to find out which server the hub should send players to.
 * Created by Toon Sevrin on 5/17/2016.
 */
public interface GameServer {
    /**
     * Gets the bungeecord id of this game server (The server name used to connect players to this game server)
     *
     * @return the bungeecord id of this game server
     */
    String getBungeeName();

    /**
     * True if players should be able to join this server (Can be as spectator or as player)
     *
     * @return true if players should be able to join this server
     */
    boolean isJoinable();

    /**
     * True if the game hasn't started yet and there is space for more players.
     *
     * @return true if the game hasn't started yet and there is space for more players
     */
    boolean isPlayable();

    /**
     * Gets the maximum amount of players allowed to play on this game server (spectators not included).
     *
     * @return the maximum amount of players allowed to play on this game server (spectators not included)
     */
    int getMaxPlayerAmount();

    /**
     * Gets the amount of players that are waiting to play/are playing on this server (spectators not included).
     *
     * @return the amount of players that are waiting to play/are playing on this server (spectators not included)
     */
    int getPlayerAmount();

    /**
     * Gets a list of all players on this server (spectators not included).
     *
     * @return a list of all players on this server (spectators not included)
     */
    Collection<SerializedPlayer> getPlayers();

    /**
     * gets the extra lore that should be displayed with this server. This can be the current map, game type, settings...
     * @return the extra lore that should be displayed with this server
     */
    List<String> getExtraLore();

    /**
     * Serializes this object to a string which can be send over redis.
     * @return serialized string of this object
     */
    String serialize();

}
