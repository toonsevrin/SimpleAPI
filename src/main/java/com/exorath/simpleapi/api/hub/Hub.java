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

package com.exorath.simpleapi.api.hub;

import com.exorath.simpleapi.api.GameHubProvider;
import com.exorath.simpleapi.api.PrimaryModule;
import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.events.EventsManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

/**
 * The API can register up to one Game. It must be registered through a {@link GameHubProvider}.
 * Created by Toon Sevrin on 5/15/2016.
 */
public abstract class Hub extends PrimaryModule {
    private World world;

    public Hub(String worldName){
        world = Bukkit.createWorld(WorldCreator.name(worldName));
        SimpleAPI.getInstance().getManager(EventsManager.class).protectWorld(world);
    }

    public Hub(){
        this("world");
    }
}
