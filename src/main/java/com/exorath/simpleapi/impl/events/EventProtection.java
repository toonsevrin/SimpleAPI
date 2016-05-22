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
import org.bukkit.event.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public class EventProtection implements Listener{
    private Set<Class> events = new HashSet<>();

    public <T extends Event & Cancellable> void cancelEvent(Class<T> cancellable) {
        if(events.contains(cancellable))
            return;
        events.add(cancellable);
        Bukkit.getPluginManager().registerEvent(cancellable, this, EventPriority.LOWEST, (listener, event) -> {
            ((Cancellable) event).setCancelled(true);
         }, SimpleAPIImpl.getInstance());
    }
    public <T extends Event & Cancellable> void uncancelEvent(Class<T> cancellable, HandlerList handlerList) {
        events.remove(cancellable);
        handlerList.unregister(this);

    }
}
