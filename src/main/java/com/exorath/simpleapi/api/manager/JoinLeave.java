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
