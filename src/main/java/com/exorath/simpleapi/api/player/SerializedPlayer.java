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

import java.util.UUID;

/**
 * Stores simple player data received over redis. Can also reserialize this data.
 * Created by Toon Sevrin on 5/17/2016.
 */
public interface SerializedPlayer {
    /**
     * Gets the unique id (Mojang UUID) of this player.
     * @return the unique id (Mojang UUID) of this player
     */
    UUID getUniqueId();

    /**
     * Gets the name of this player (Can be outdated if player quickly changes name after the message is send).
     * @return the name of this player
     */
    String getName();

    /**
     * Serializes this object to a string which can be send over redis.
     * @return serialized string of this object
     */
    String serialize();
}
