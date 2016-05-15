package com.exorath.simpleapi.api.module;



/**
 * The SimpleAPI is capable of handling multiple Modules. They are registered with a JavaPlugin that implements ModuleProvider (SimpleAPI automatically registers them on PluginLoadEvent).
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface ModuleProvider {
    /**
     * Creates and returns a new {@link Module} object. This method will only be called once on this provider during the server runtime.
     *
     * @return the newly created game
     */
    Module create();

    /**
     * Gets the id of this provider, typically the plugin's name (Avoid spaces).
     *
     * @return the id of this provider
     */
    String getID();
}
