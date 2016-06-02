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

package com.exorath.simpleapi.api.game.minigame.gamestate;

import com.exorath.simpleapi.api.manager.Manager;

/**
 * Created by Toon Sevrin on 6/1/2016.
 */
public interface GameStateManager extends Manager {
    /**
     * Gets the current state of the game.
     * @return the current state of the game
     */
    GameState getState();

    /**
     * Sets the current state of the game and calls a {@Link GameStateChangedEvent}.
     * @param state state to set game to.
     */
    void setState(GameState state);

    /**
     * Sets the current state of the game to the upcoming {@link GameState}.
     */
    void nextState();
}
