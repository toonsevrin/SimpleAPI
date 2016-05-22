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

package com.exorath.simpleapi.impl;

import com.exorath.simpleapi.api.GameHubProvider;
import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.game.Game;
import com.exorath.simpleapi.api.hub.Hub;
import com.exorath.simpleapi.api.manager.Manager;
import com.exorath.simpleapi.api.module.Module;
import com.exorath.simpleapi.impl.database.DBManager;
import com.google.common.collect.ImmutableList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public class SimpleAPIImpl extends JavaPlugin implements SimpleAPI {
    private static SimpleAPIImpl instance;

    private final List<Manager> managers = new ArrayList<>();
    private final List<Module> modules = new ArrayList<>();

    private List<Runnable> onDisables = new ArrayList<>();

    private Game game = null;

    @Override
    public void onEnable() {
        instance = this;
        addManager(new DBManager("mongodb"));
    }

    @Override
    public void onDisable() {
        managers.stream().forEach(m -> m.destroy());
        modules.stream().forEach(m -> m.destroy());
        if(game != null)
            game.destroy();
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public Hub getHub() {
        return null;
    }

    @Override
    public GameHubProvider getGameHubProvider() {
        return null;
    }

    @Override
    public List<Manager> getManagers() {
        return ImmutableList.copyOf(managers);
    }

    @Override
    public void addManager(Manager manager) {
        if (Arrays.asList(manager.getClass().getInterfaces()).contains(Manager.class))
            if (!managers.stream().filter(m -> manager.getClass().isAssignableFrom(m.getClass())).findAny().isPresent())
                managers.add(manager);
    }

    @Override
    public void removeManager(Manager manager) {
        managers.remove(manager);
    }

    @Override
    public <T extends Manager> T getManager(Class<T> clazz) {
        if (clazz == null || clazz == Manager.class)
            return null;
        return (T) managers.stream().filter(m -> clazz.isAssignableFrom(m.getClass())).findAny().orElse(null);
    }

    @Override
    public void addModule(Module module) {
        if(!modules.stream().filter(m -> module.getClass().equals(m.getClass())).findAny().isPresent())
            modules.add(module);
    }

    @Override
    public <T extends Module> T getModule(Class<T> clazz) {
        if (clazz == null || clazz == Module.class)
            return null;
        return (T) modules.stream().filter(m -> clazz.isAssignableFrom(m.getClass())).findAny().orElse(null);
    }

    @Override
    public void addOnDisable(Runnable runnable) {
        onDisables.add(runnable);
    }

    @Override
    public void log(String message) {
        message = message.replace("\n", "\n[GameAPI] [INFO] ");
        System.out.println("[GameAPI] [INFO] " + message);
    }

    @Override
    public void logError(String error) {
        error = error.replace("\n", "\n[GameAPI] [ERROR] ");
        System.err.println("[GameAPI] [ERROR] " + error);
    }

    public static DBManager getDatabaseManager(){
        return instance.getManager(DBManager.class);
    }

    public static SimpleAPIImpl getInstance(){
        return instance;
    }
}
