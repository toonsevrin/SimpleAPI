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

import com.exorath.simpleapi.api.serverlist.GameServer;

/**
 * Interface extended by ServerSignDisplay and ServerNpcDisplay. You can create your own server displays as well.
 * Created by Toon Sevrin on 5/29/2016.
 */
public interface ServerDisplay {

    /**
     * Filters allow you to only display certain servers on the display (fe. ranked servers). They are registered to the Hub by ID.
     * This method returns the filter id this display should be filtered with.
     * @return this method returns the filter id the servers on this display should be filtered with
     */
    String getFilterId();

    /**
     * Called whenever an {@link GameServer} is added or updated (If this ServerDisplay is added to the ServerDisplayManager).
     * @param server server that should be registered to this display
     */
    void register(GameServer server);

    /**
     * Called whenever an {@link GameServer} should be removed from this {@link ServerDisplay}.
     * @param server server that should be removed from the display
     */
    void deregister(GameServer server);
}
