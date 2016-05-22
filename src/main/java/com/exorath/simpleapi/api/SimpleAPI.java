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

package com.exorath.simpleapi.api;

import com.exorath.simpleapi.api.game.Game;
import com.exorath.simpleapi.api.hub.Hub;
import com.exorath.simpleapi.api.manager.Manager;
import com.exorath.simpleapi.api.module.Module;

import java.io.File;
import java.util.List;

/**
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface SimpleAPI {
    /**
     * Returns the registered game, or null if no game has been registered
     * @return the registered game
     */
    Game getGame();
    /**
     * Returns the registered hub, or null if no game has been registered
     * @return the registered hub
     */
    Hub getHub();

    /**
     * Returns the automatically registered {@link GameHubProvider}, or null if no {@link GameHubProvider} was registered.
     * @return the registered {@link GameHubProvider}, or null if no {@link GameHubProvider} was registered
     */
    GameHubProvider getGameHubProvider();
    /**
     * Gets a list of all registered Managers.
     *
     * @return the list of registered managers
     */
    List<Manager> getManagers();

    /**
     * Adds a manager to the list of managers, or does nothing if a manager with provided manager class was already added.
     *
     * @param manager the manager to be added
     */
    void addManager(Manager manager);

    /**
     * Removes a provided manager if it is in the manager list.
     *
     * @param manager the manager to remove
     */
    void removeManager(Manager manager);

    /**
     * Gets the acting manager instance of the provided class, or null if no manager of this class is registered.
     *
     * @param clazz the class of which the manager is intended to extend
     * @param <T>   the type of manager that should be returned
     * @return the manager instance of the provided class, or null if no manager of this class is registered
     */
    <T extends Manager> T getManager(Class<T> clazz);

    /**
     * Adds a modules to the list of modules, or does nothing if a module with provided module class was already added.
     * Note that this is done automatically if a JavaPlugin implements ModuleProvider.
     *
     * @param module the module to be added
     */
    void addModule(Module module);

    /**
     * Gets the acting {@link Module} instance of the provided class, or null if no {@link Module} of this class is registered.
     *
     * @param clazz the class of which the module is intended to extend
     * @param <T>   the parameterized type
     * @return the module if there is one of this type, null if no module of this type is set
     */
    <T extends Module> T getModule(Class<T> clazz);


    /**
     * Adds a runnable to the GameAPI that will surely be executed on disable. Useful for closing sockets.
     * @param runnable runnable that will be executed on disable
     */
    void addOnDisable(Runnable runnable);

    /**
     * Gets the plugin folder of the GameAPI, useful for reading config files.
     * @return the plugin folder of the GameAPI
     */
    File getDataFolder();
    /**
     * Logs a message with [SimpleAPI] [INFO] prefix to the console
     * @param message message to log
     */
    void log(String message);
    /**
     * Logs an error with [SimpleAPI] [ERROR] prefix to the console
     * @param error error to log
     */
    void logError(String error);

    static SimpleAPI getInstance(){
        return null;//TODO return implementation!!
    }
}
