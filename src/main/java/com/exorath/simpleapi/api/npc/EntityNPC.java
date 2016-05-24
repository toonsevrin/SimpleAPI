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
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/**
 * Created by Toon Sevrin on 5/22/2016.
 */
public interface EntityNPC extends NPC {

    /**
     * Gets the name assigned to this entity
     * @return the name assigned to this entity
     */
    String getName();
    /**
     * Makes this entity look at the target {@link Location}.
     * @param location {@link Location} to make this entity look at
     */
    void lookAt(Location location);

    /**
     * Sets the name of this entity
     * @param name name to assign to entity
     */
    void setName(String name);

    /**
     * True if gravity is enabled on this entity.
     * @return true if gravity is enabled on this entity
     */
    boolean hasGravity();
    /**
     * Enable or disable gravity drag on this entity
     * @param gravity whether or not to enable gravity on this entity
     */
    void setGravity(boolean gravity);

    /**
     * True if entity collision is enabled
     * @return true if entity collision is enabled
     */
    boolean hasCollision();

    /**
     * Sets whether or not this entity should have collision
     * @param flag whether or not this entity should have collision
     */
    void setCollision(boolean flag);

    /**
     * Sets the pitch of this entity, measured in degrees.
     * A pitch of 0 represents level forward facing.
     * A pitch of 90 represents downward facing, or negative y direction.
     * A pitch of -90 represents upward facing, or positive y direction.
     * @param pitch pitch to make entity look at
     */
    void setPitch(float pitch);

    /**
     * Sets the yaw of this entity, measured in degrees.
     * A yaw of 0 or 360 represents the positive z direction.
     * A yaw of 180 represents the negative z direction.
     * A yaw of 90 represents the negative x direction.
     * A yaw of 270 represents the positive x direction.
     * @param yaw yaw to make entity look at
     */
    void setYaw(float yaw);


    /**
     * Set the strength of the gravity on this entity
     * @param gravity strength of the gravity drag
     */
    void setGravity(float gravity);

    /**
     * True if this entity is frozen in place.
     * @return true if this entity is frozen in place
     */
    boolean isFrozen();

    /**
     * Sets whether or not this entity should be frozen in place
     * @param flag whether or not this entity should be frozen in place
     */
    void setFrozen(boolean flag);

    /**
     * True if this entity cannot be damaged
     * @return true if this entity cannot be damaged
     */
    boolean isInvulnerable();

    /**
     * Sets whether or not this entity is invulnerable
     * @param invulnerable sets whether or not this entity is invulnerable
     */
    void setInvulnerable(boolean invulnerable);

    /**
     * Gets the bukkit entity of this EntityNPC
     * @return the bukkit entity of this EntityNPC
     */
    LivingEntity getBukkitEntity();

}
