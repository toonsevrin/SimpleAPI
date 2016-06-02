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

/**
 * Created by Toon Sevrin on 6/1/2016.
 */
public enum GameState {
    WAITING(GameState.COUNTING_DOWN),
    COUNTING_DOWN(GameState.PLAYING),
    PLAYING(GameState.RESETTING),
    RESETTING(GameState.WAITING);

    private GameState nextState;
    GameState(GameState nextState){
        this.nextState = nextState;
    }

    /**
     * Returns the next {@link GameState}.
     * @return the next {@link GameState}
     */
    public GameState getNextState() {
        return nextState;
    }
}
