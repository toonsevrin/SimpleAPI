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

package com.exorath.simpleapi.impl.events;

import com.exorath.simpleapi.impl.SimpleAPIImpl;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public class WorldProtection implements Listener {
    private static final Class[] CANCELLED_EVENTS = new Class[]{BlockBreakEvent.class, BlockPlaceEvent.class, BlockBurnEvent.class, BlockDamageEvent.class, BlockDispenseEvent.class,
            BlockFadeEvent.class, BlockFormEvent.class, BlockFromToEvent.class, BlockGrowEvent.class, BlockIgniteEvent.class, BlockPhysicsEvent.class, BlockPistonEvent.class,
            BlockSpreadEvent.class, EntityBlockFormEvent.class, LeavesDecayEvent.class, SignChangeEvent.class, EntityBreakDoorEvent.class, EntityChangeBlockEvent.class, EntityCreatePortalEvent.class
    };

    private Set<World> worlds = new HashSet<>();

    public WorldProtection() {
        //Cancel all events in CANCELLED_EVENTS if they are in the protectedWorlds collection.
        Bukkit.getPluginManager().registerEvent(BlockBreakEvent.class, this, EventPriority.LOWEST, (listener, event) -> {
            if (event instanceof Cancellable)
                cancelEvent((Cancellable) event);
        }, SimpleAPIImpl.getInstance());
    }

    private void cancelEvent(Cancellable event) {
        if (event instanceof BlockEvent) {
            if (worlds.contains(((BlockEvent) event).getBlock().getWorld()))
                ((Cancellable) event).setCancelled(true);
        } else if (event instanceof EntityEvent)
            if (worlds.contains(((EntityEvent) event).getEntity().getWorld()))
                ((Cancellable) event).setCancelled(true);
    }

    public void protectWorld(World world) {
        worlds.add(world);
    }


    public void unprotectWorld(World world) {
        worlds.remove(world);
    }

    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        if (worlds.contains(event.getBlock().getWorld()))
            event.setBuildable(false);
    }
}
