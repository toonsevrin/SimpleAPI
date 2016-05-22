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
import com.exorath.simpleapi.api.game.Game;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public abstract class Minigame extends Game {
    public static String GAME_SERVERS_PREFIX = "gameservers.";

    /**
     * Gets the redis channel where games of this GameHubProvider publish their status to
     * @return the redis channel where games of this GameHubProvider publish their status to, or null if no GameHubProvider registered.
     */
    public static String getRedisGameDiscoveryChannel(){
        return SimpleAPI.getInstance().getGameHubProvider() == null ? null : GAME_SERVERS_PREFIX + SimpleAPI.getInstance().getGameHubProvider().getID();
    }
}
