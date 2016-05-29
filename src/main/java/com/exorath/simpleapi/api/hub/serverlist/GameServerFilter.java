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

package com.exorath.simpleapi.api.hub.serverlist;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This interface allows you to filter GameServers out o a collection of GameServers. If you'd like
 * Created by Toon Sevrin on 5/29/2016.
 */
public interface GameServerFilter {
    boolean allowed(GameServer server);
    Collection<GameServer> filter(Collection<GameServer> servers);

    class RankedFilter implements GameServerFilter {
        @Override
        public boolean allowed(GameServer server) {
            return server.getExtraLore().stream().filter(l -> l.contains("RANKED")).findAny().isPresent();
        }

        @Override
        public List<GameServer> filter(Collection<GameServer> servers) {
            return servers.stream().filter(s -> allowed(s)).collect(Collectors.toList());
        }
    }
}
