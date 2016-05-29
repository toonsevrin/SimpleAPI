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

package com.exorath.tdm;

import com.exorath.simpleapi.api.GameHubProvider;
import com.exorath.simpleapi.api.game.Game;
import com.exorath.simpleapi.api.hub.Hub;
import com.exorath.tdm.game.TDMGame;
import com.exorath.tdm.hub.TDMHub;

/**
 * The server will either decide to load this server as a hub or as a game.
 * Either createGame() or createHub() is called, never both and never twice during runtime.
 *
 * This provider is automatically discovered by the GameAPI because it's a JavaPlugin.
 * Note that this class must be referenced from the plugin.yml!
 *
 * Created by Toon Sevrin on 5/25/2016.
 */
public class TDMProvider extends GameHubProvider{

    //Creates a TDMGame instance, this instance is used for the rest of runtime.
    @Override
    public Game createGame() {
        return new TDMGame();
    }

    //Creates a TDMHub instance, this instance is used for the rest of runtime.
    @Override
    public Hub createHub() {
        return new TDMHub();
    }

    //The ID is used for database referencing and must be unique for this game type.
    @Override
    public String getID() {
        return "TDM";
    }
}
