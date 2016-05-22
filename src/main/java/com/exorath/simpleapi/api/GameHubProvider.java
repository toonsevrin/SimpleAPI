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

package com.exorath.simpleapi.api;

import com.exorath.simpleapi.api.game.Game;
import com.exorath.simpleapi.api.hub.Hub;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * If a GameHubProvider is loaded as a plugin, it will be detected by the GameAPI and one of the create() methods will be ran (depending on a configuration).
 * Registering a game or hub can only be done through this method.
 * Created by Toon Sevrin on 5/15/2016.
 */
public abstract class GameHubProvider  extends JavaPlugin {
    /**
     * Creates and returns a new {@link Game} object. This method will only be called once during the server runtim and will never be run along createHub().
     *
     * @return the newly created game
     */
    public abstract Game createGame();

    /**
     * Creates and returns a new {@link Hub} object. This method will only be called once during the server runtim and will never be run along createGame().
     *
     * @return the newly created game
     */
    public abstract Hub createHub();


    /**
     * Gets the id of this provider, typically the plugin's name (Should not contain spaces).
     * This can be used as a database identifier
     *
     * @return the id of this provider
     */
    public abstract String getID();
}
