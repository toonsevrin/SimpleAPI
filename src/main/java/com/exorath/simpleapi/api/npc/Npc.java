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

package com.exorath.simpleapi.api.npc;

import org.bukkit.Location;

/**
 * Created by Toon Sevrin on 5/22/2016.
 */
public interface NPC {
    /**
     * Gets the {@link Location} of the entity.
     * @return the {@link Location} of the entity
     */
    Location getLocation();

    /**
     * Teleports this entity to a specific {@link Location}
     * @param location location to teleport entity towards
     */
    void teleport(Location location);

    /**
     * Despawns the entity out of the world.
     */
    void despawn();
}
