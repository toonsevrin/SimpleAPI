package com.exorath.simpleapi.api.manager;

import com.exorath.simpleapi.api.player.GamePlayer;

/**
 * If JoinLeave is implemented by a manager (registered in the API's Manager list), events will be called.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface JoinLeave {
    /**
     * Run when the player joins a game. Bukkit player loaded.
     *
     * @param player the player that is joining the server
     */
    void join(GamePlayer player);

    /**
     * Run when a player leaves a game.
     *
     * @param player the player that is leaving the server
     */
    void leave(GamePlayer player);
}
