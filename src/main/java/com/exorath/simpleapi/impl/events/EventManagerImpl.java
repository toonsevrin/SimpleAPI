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

import com.exorath.simpleapi.api.events.EventManager;
import com.exorath.simpleapi.api.manager.Manager;
import com.exorath.simpleapi.impl.SimpleAPIImpl;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * The events manager disables all world modifying events within it's worlds list.
 * Created by Toon Sevrin on 5/15/2016.
 */
public class EventManagerImpl implements Manager, EventManager {
    private WorldProtection worldProtection = new WorldProtection();
    private EventProtection eventProtection = new EventProtection();

    public EventManagerImpl(){
        Bukkit.getPluginManager().registerEvents(worldProtection, SimpleAPIImpl.getInstance());
        Bukkit.getPluginManager().registerEvents(eventProtection, SimpleAPIImpl.getInstance());
    }

    @Override
    public void protectWorld(World world) {
        worldProtection.protectWorld(world);
    }

    @Override
    public void unprotectWorld(World world) {
        worldProtection.unprotectWorld(world);
    }

    @Override
    public <T extends Event & Cancellable> void cancelEvent(Class<T> cancellable) {
        eventProtection.cancelEvent(cancellable);
    }

    @Override
    public <T extends Event & Cancellable> void unCancelEvent(Class<T> cancellable, HandlerList handlerList) {
        eventProtection.uncancelEvent(cancellable, handlerList);
    }
}
