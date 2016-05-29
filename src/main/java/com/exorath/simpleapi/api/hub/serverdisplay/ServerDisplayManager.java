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

package com.exorath.simpleapi.api.hub.serverdisplay;

import com.exorath.simpleapi.api.hub.serverlist.GameServerFilter;
import com.exorath.simpleapi.api.manager.Manager;

import java.util.HashMap;

/**
 * Created by Toon Sevrin on 5/29/2016.
 */
public interface ServerDisplayManager extends Manager {
    /**
     * Adds a filter that may be used by the game selector signs and NPCs. Do not use isJoinable() or isPlayable() here, you can sort within these filters.
     *
     * @param id     filter id to reference to this filter (from the exorath.yml world file)
     * @param filter filter to add
     */
    void addFilter(String id, GameServerFilter filter);

    /**
     * Gets a map of all filters that were added. These may be used by the game selector signs and NPCs.
     *
     * @return a map of all filters that were added
     */
    HashMap<String, GameServerFilter> getFilters();

    /**
     * Adds a display to the displayManager.
     * Note that signs and npcs defined in the exorath.yml world folder are handled automatically.
     *
     * @param display display to add
     */
    void addDisplay(ServerDisplay display);

    /**
     * Removes a display from the display list.
     *
     * @param display display to remove
     */
    void removeDisplay(ServerDisplay display);
}
