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

package com.exorath.simpleapi.api.game.minigame;

import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.events.EventManager;
import com.exorath.simpleapi.api.game.Game;
import com.exorath.simpleapi.api.player.GamePlayer;
import com.exorath.simpleapi.impl.game.minigame.gamestate.GameStateManagerImpl;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.Collection;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public abstract class Minigame extends Game {
    private World lobbyWorld;
    private boolean repeating = false;

    public Minigame(String gameName) {
        this("world", gameName);
    }

    public Minigame(String worldName, String gameName) {
        super(gameName);
        loadWorld(worldName);
        SimpleAPI.getInstance().addManager(new GameStateManagerImpl(this));
    }

    private void loadWorld(String worldName) {
        lobbyWorld = Bukkit.createWorld(WorldCreator.name(worldName));
        SimpleAPI.getInstance().getManager(EventManager.class).protectWorld(lobbyWorld);
    }

    public World getLobbyWorld() {
        return lobbyWorld;
    }

    /**
     * Sets the world players should wait inside while the game is waiting for players. Players will spawn at  {@link World#getSpawnLocation()}
     *
     * @param lobbyWorld the world of the lobby
     */
    public void setLobbyWorld(World lobbyWorld) {
        this.lobbyWorld = lobbyWorld;
    }

    /**
     * Sets whether or not the players should remain on the server when the game is finished.
     *
     * @param repeating true will teleport players to the lobbyWorld on finish, false will connect players to the game's hub server on finish
     */
    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    /**
     * Gets whether or not the players should remain on the server when the game is finished.
     *
     * @return whether or not the players should remain on the server when the game is finished
     */
    public boolean isRepeating() {
        return repeating;
    }

    /**
     * This method is called when the game is about to start (GameState changed to PLAYING).
     */
    public abstract void start();

    @Override
    public Collection<GamePlayer> getPlayers() {
        return null;
    }

    /**
     * Connects the given GamePlayer to an available game hub server
     * @param player player to connect to an available game hub server
     */
    public static void connectToGameHub(GamePlayer player){

    }
}
