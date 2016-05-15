package com.exorath.simpleapi.api;

import com.exorath.simpleapi.api.game.Game;
import com.exorath.simpleapi.api.manager.Manager;
import com.exorath.simpleapi.api.module.Module;
import com.exorath.simpleapi.api.player.GamePlayer;

import java.util.Collection;
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
     * Gets a list of all registered Managers.
     *
     * @return the list of registered managers
     */
    List<Manager> getManagers();

    /**
     * Adds a manager to the list of managers.
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
     * Adds a modules to the list of modules. Note that this is done automatically if a JavaPlugin implements ModuleProvider.
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
     * Gets a collection of all online {@link GamePlayer}s. These players may or may not be playing the game.
     *
     * @return a set of all online {@link GamePlayer}s.
     */
     Collection<GamePlayer> getOnlinePlayers();

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
