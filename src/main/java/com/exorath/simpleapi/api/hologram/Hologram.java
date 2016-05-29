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

package com.exorath.simpleapi.api.hologram;

import com.exorath.simpleapi.impl.hologram.HologramImpl;
import org.bukkit.Location;


/**
 * Holograms are nametags without a visible entity.
 * This API is a quick overlay on another API, it may change when we write GameAPIv2 due to lines not being abstracted.
 * Created by Toon Sevrin on 5/27/2016.
 */
public interface Hologram {

    /**
     * Creates a new {@link Hologram}, ready to be spawned at the given location (Spawn with {@link Hologram#spawn()} method)
     * @param location location to spawn {@link Hologram} at
     * @param text text to display on {@link Hologram}
     * @return the newly created {@link Hologram}
     */
    static Hologram createHologram(Location location, String text){
        return HologramImpl.createHologram(location, text);
    }
    /**
     * Adds a {@link Hologram} line above this hologram.
     *
     * @param line line to add
     */
    void addLineAbove(String line);

    /**
     * Adds a {@link Hologram} line below this hologram.
     *
     * @param line line to add
     */
    void addLineBelow(String line);

    /**
     * Removes the line above this Hologram.
     * @return true if a hologram was removed
     */
    boolean removeLineAbove();

    /**
     * Removes the line below this Hologram.
     * @return true if a hologram was removed
     */
    boolean removeLineBelow();

    /**
     * Adds a {@link TouchHandler} to the hologram, it's called whenever a player interacts with it.
     *
     * @param touchHandler {@link TouchHandler} to add to this Hologram
     */
    void addTouchHandler(TouchHandler touchHandler);

    /**
     * Sets whether or not {@link com.exorath.simpleapi.api.player.GamePlayer}s can click this {@Link Hologram} to call the {@link Hologram}'s {@link TouchHandler}s.
     * @param touchable whether or not the {@link Hologram} should be touchable.
     */
    void setTouchable(boolean touchable);
    /**
     * Adds a {@link ViewHandler} to the hologram, it's called whenever a player views(/loads) this hologram.
     * The {@link ViewHandler} can add {@Link GamePlayer} specific properties.
     *
     * @param viewHandler {@link ViewHandler} to add to this Hologram
     */
    void addViewHandler(ViewHandler viewHandler);

    /**
     * Removes all {@link TouchHandler}s from the {@link Hologram}.
     */
    void clearTouchHandlers();

    /**
     * Removes all {@link ViewHandler}s from the {@link Hologram}.
     */
    void clearViewHandlers();

    /**
     * Spawns this {@link Hologram} in the world.
     */
    void spawn();

    /**
     * Spawns this {@link Hologram} in the world and despawns it after the specified timeout.
     * @param ticks timeout to despawn {@link Hologram} after
     */
    void spawn(long ticks);
    /**
     * Despawns this {@link Hologram} from the world.
     */
    void despawn();

    /**
     * Updates the content of the hologram (Sends updated packets to all players).
     */
    void update();

    /**
     * Teleports the Hologram to a given Location.
     * @param location location to teleport the {@link Hologram} to.
     */
    void teleport(Location location);


}
