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

package com.exorath.tdm.game;

import com.exorath.simpleapi.api.game.minigame.Minigame;
import com.exorath.simpleapi.api.player.GamePlayer;
import org.bukkit.Location;

/**
 * Created by Toon Sevrin on 5/25/2016.
 */
public class TDMGame extends Minigame {
    public static String GAME_NAME = "Team Deathmatch";

    public TDMGame() {
        super(GAME_NAME);
    }


    @Override
    public void start() {
        startPlayers();
    }

    /**
     * Teleports players to their spawn lcoation and sends them a message that the game has started.
     */
    private void startPlayers(){
        for (GamePlayer player : getPlayers()){
            Location spawn = null;
            player.bukkit().teleport(spawn);
            player.bukkit().sendMessage("TDM has started.");
        }
    }
}
