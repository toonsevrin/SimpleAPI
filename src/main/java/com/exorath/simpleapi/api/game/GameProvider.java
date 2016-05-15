package com.exorath.simpleapi.api.game;


/**
 * If a JavaPlugin is registered with this GameProvider, it will be detected by the GameAPI and the create() method will be ran.
 * Registering the game must be done through this method (public class <<Mainclass>> extends JavaPlugin implements GameProvider)
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface GameProvider {
    /**
     * Creates and returns a new {@link Game} object. This method will only be called once during the server runtime.
     *
     * @return the newly created game
     */
    Game create();

    /**
     * Gets the id of this provider, typically the plugin's name (Avoid spaces).
     *
     * @return the id of this provider
     */
    String getID();
}
