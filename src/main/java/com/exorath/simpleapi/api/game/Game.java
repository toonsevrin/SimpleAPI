package com.exorath.simpleapi.api.game;

import com.exorath.simpleapi.api.lib.Destroyable;
import com.exorath.simpleapi.api.properties.Properties;

/**
 * The API can register up to one Game. It must be registered through a GameProvider.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface Game extends Destroyable{


    /**
     * Gets the {@link Properties} corresponding to this Game.
     *
     * @return The properties.
     */
     Properties getProperties();

    /**
     * Gets this Game's name. This may be used for display purposes.
     *
     * @return The name.
     */
     String getName();

    /**
     * Sets this Game's name. This may be used for display purposes.
     *
     * @param name The new name.
     */
    void setName(String name);
}
