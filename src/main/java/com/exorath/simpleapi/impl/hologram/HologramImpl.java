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

package com.exorath.simpleapi.impl.hologram;

import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.hologram.Hologram;
import com.exorath.simpleapi.api.hologram.TouchAction;
import com.exorath.simpleapi.api.hologram.TouchHandler;
import com.exorath.simpleapi.api.hologram.ViewHandler;
import de.inventivegames.hologram.HologramAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Toon Sevrin on 5/28/2016.
 */
public class HologramImpl implements Hologram{
    private de.inventivegames.hologram.Hologram hologram;

    private HologramImpl(Location location, String text){
        hologram = HologramAPI.createHologram(location, text);
    }

    @Override
    public void addLineAbove(String line) {
        hologram.addLineAbove(line);
    }

    @Override
    public void addLineBelow(String line) {
        hologram.addLineBelow(line);
    }

    @Override
    public boolean removeLineAbove() {
        return hologram.removeLineAbove();
    }

    @Override
    public boolean removeLineBelow() {
        return hologram.removeLineBelow();
    }

    //TODO: Get GamePlayer by Player
    @Override
    public void addTouchHandler(TouchHandler touchHandler) {
        hologram.addTouchHandler((hologram, player, touchAction) ->
                touchHandler.onTouch(HologramImpl.this, null, getTouchAction(touchAction)));
    }

    @Override
    public void setTouchable(boolean touchable) {
        hologram.setTouchable(touchable);
    }

    @Override
    public void addViewHandler(ViewHandler viewHandler) {
        hologram.addViewHandler((hologram, player, line) ->
                viewHandler.onView(HologramImpl.this, null, line));
    }

    @Override
    public void clearTouchHandlers() {
        hologram.clearTouchHandlers();
    }

    @Override
    public void clearViewHandlers() {
        hologram.clearViewHandlers();
    }

    @Override
    public void spawn() {
        hologram.spawn();
    }

    @Override
    public void spawn(long ticks) {
        hologram.spawn(ticks);
    }

    @Override
    public void despawn() {
        hologram.despawn();
    }

    @Override
    public void update() {
        hologram.update();
    }

    @Override
    public void teleport(Location location) {
        hologram.move(location);
    }

    public de.inventivegames.hologram.Hologram getHologram() {
        return hologram;
    }

    private TouchAction getTouchAction(de.inventivegames.hologram.touch.TouchAction touchAction){
        switch (touchAction){
            case RIGHT_CLICK:
                return TouchAction.RIGHT_CLICK;
            case LEFT_CLICK:
                return TouchAction.LEFT_CLICK;
            case UNKNOWN:
                return TouchAction.UNKNOWN;
        }
        return TouchAction.UNKNOWN;
    }

    public static Hologram createHologram(Location location, String text){
        if(!Bukkit.getPluginManager().isPluginEnabled("HologramAPI")){
            SimpleAPI.getInstance().logError("HologramAPI not enabled!");
            return null;
        }
        return new HologramImpl(location, text);
    }
}
