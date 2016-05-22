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

package com.exorath.simpleapi.api.events;

import com.exorath.simpleapi.api.manager.Manager;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Manager that handles world events and other event handling.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface EventsManager extends Manager {
    /**
     * Cancels following events on {@link org.bukkit.event.EventPriority#LOWEST} within the world: BlockBreakEvent.class, BlockPlaceEvent.class, BlockBurnEvent.class, BlockDamageEvent.class, BlockDispenseEvent.class,BlockFadeEvent.class, BlockFormEvent.class, BlockFromToEvent.class, BlockGrowEvent.class, BlockIgniteEvent.class, BlockPhysicsEvent.class, BlockPistonEvent.class,BlockSpreadEvent.class, EntityBlockFormEvent.class, LeavesDecayEvent.class, SignChangeEvent.class, EntityBreakDoorEvent.class, EntityChangeBlockEvent.class, EntityCreatePortalEvent.class
     *
     * @param world world to protect against modification
     */
    void protectWorld(World world);

    /**
     * Removes world protection added by protectWorld method.
     *
     * @param world world to remove protection from
     */
    void unprotectWorld(World world);

    /**
     * When this event is triggered, it will be cancelled on {@link org.bukkit.event.EventPriority#LOWEST}.
     *
     * @param cancellable event to automatically cancel
     */
    <T extends Event & Cancellable> void cancelEvent(Class<T> cancellable);

    /**
     * Removes the automatically cancelling of an event registered with cancelEvent
     *
     * @param cancellable event to remove automatically cancel from
     * @param handlerList the handlerList of this event to remove the listener from
     */
    <T extends Event & Cancellable> void unCancelEvent(Class<T> cancellable, HandlerList handlerList);
}
