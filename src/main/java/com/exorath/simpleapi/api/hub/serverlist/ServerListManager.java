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

/**
 * The ServerListManager is responsible for listing the available game servers that serverlist to this hub.
 * Created by Toon Sevrin on 5/17/2016.
 */
public interface ServerListManager {
    Collection<GameServer> getGameServers();

}
