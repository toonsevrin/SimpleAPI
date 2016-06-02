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

package com.exorath.simpleapi.impl.game.minigame.gamestate;

import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.game.minigame.Minigame;
import com.exorath.simpleapi.api.game.minigame.gamestate.GameState;
import com.exorath.simpleapi.api.game.minigame.gamestate.GameStateChangedEvent;
import com.exorath.simpleapi.api.game.minigame.gamestate.GameStateManager;
import com.exorath.simpleapi.api.player.GamePlayer;
import com.exorath.simpleapi.api.player.PlayerManager;
import org.bukkit.Bukkit;

/**
 * Created by Toon Sevrin on 6/1/2016.
 */
public class GameStateManagerImpl implements GameStateManager {
    private Minigame game;
    private GameState state = GameState.WAITING;

    public GameStateManagerImpl(Minigame game) {
        this.game = game;
    }

    @Override
    public GameState getState() {
        return state;
    }

    @Override
    public void setState(GameState newState) {
        GameState oldState = this.state;
        if (oldState == newState)
            return;
        this.state = newState;
        Bukkit.getPluginManager().callEvent(new GameStateChangedEvent(oldState, newState));
        onStateChanged();
    }

    private void onStateChanged() {
        switch (state) {
            case WAITING:
                setWaiting();
                break;
            case COUNTING_DOWN:
                setCountingDown();
                break;
            case PLAYING:
                setPlaying();
                break;
            case RESETTING:
                setResetting();
                break;
        }
    }

    private void setWaiting() {
        if (game.isRepeating())
            SimpleAPI.getInstance().getManager(PlayerManager.class).getPlayers().forEach(p -> p.bukkit().teleport(game.getLobbyWorld().getSpawnLocation()));
        else
            SimpleAPI.getInstance().getManager(PlayerManager.class).getPlayers().forEach(p -> SimpleAPI.connect(p, );

    }

    private void setCountingDown() {
        countdown.start();
    }

    private void setPlaying() {
        game.start();
    }

    private void setResetting() {
        //resetWorld();
    }

    @Override
    public void nextState() {
        setState(state.getNextState());
    }
}
