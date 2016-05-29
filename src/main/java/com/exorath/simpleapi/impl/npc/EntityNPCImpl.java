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

package com.exorath.simpleapi.impl.npc;

import com.exorath.simpleapi.api.npc.EntityNPC;
import de.inventivegames.npc.NPC;
import de.inventivegames.npc.NPCLib;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

/**
 * Created by Toon Sevrin on 5/25/2016.
 */
public class EntityNPCImpl implements EntityNPC {
    private NPC npc;

    public EntityNPCImpl(Location location, EntityType type, String name) {
        if (type == EntityType.PLAYER)
            npc = NPCLib.spawnPlayerNPC(location, name);
        else
            npc = NPCLib.spawnNPC(type, location, name);
    }

    @Override
    public String getName() {
        return npc.getName();
    }

    @Override
    public void lookAt(Location location) {
        npc.lookAt(location);
    }

    @Override
    public void setName(String name) {
        //TODO: Implement NickNamer and set name through that!!
        npc.getBukkitEntity().setCustomName(name);
    }

    @Override
    public boolean hasGravity() {
        return npc.hasGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        npc.setGravity(gravity);

    }

    @Override
    public boolean hasCollision() {
        return npc.hasCollision();
    }

    @Override
    public void setCollision(boolean flag) {
        npc.setCollision(flag);
    }

    @Override
    public void setPitch(float pitch) {
        npc.setPitch(pitch);
    }

    @Override
    public void setYaw(float yaw) {
        npc.setYaw(yaw);
    }

    @Override
    public void setGravity(float gravity) {
        npc.setGravity(gravity);
    }

    @Override
    public boolean isFrozen() {
        return npc.isFrozen();
    }

    @Override
    public void setFrozen(boolean frozen) {
        npc.setFrozen(frozen);
    }

    @Override
    public boolean isInvulnerable() {
        return npc.isInvulnerable();
    }

    @Override
    public void setInvulnerable(boolean invulnerable) {
        npc.setInvulnerable(invulnerable);
    }

    @Override
    public LivingEntity getBukkitEntity() {
        return npc.getBukkitEntity();
    }

    @Override
    public Location getLocation() {
        return npc.getLocation();
    }

    @Override
    public void teleport(Location location) {
        npc.teleport(location);
    }

    @Override
    public void despawn() {
        npc.despawn();
    }
}
