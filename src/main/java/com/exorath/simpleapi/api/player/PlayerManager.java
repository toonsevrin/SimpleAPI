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

package com.exorath.simpleapi.api.player;

import com.exorath.simpleapi.api.manager.Manager;

import java.util.Collection;
import java.util.Set;

/**
 * The PlayerManagerImpl is added to the SimpleAPI's manager list onEnable. It mainly manages GamePlayer loading, storage and saving
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface PlayerManager extends Manager{
    /**
     * Gets a collection of all online {@link GamePlayer}s. These players may or may not be playing the game.
     *
     * @return a set of all online {@link GamePlayer}s
     */
    Collection<GamePlayer> getPlayers();
}
