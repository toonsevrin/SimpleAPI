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

package com.exorath.simpleapi.impl.hub.serverdisplay;

import com.exorath.simpleapi.api.SimpleAPI;
import com.exorath.simpleapi.api.hub.Hub;
import com.exorath.simpleapi.api.hub.serverdisplay.ServerDisplay;
import com.exorath.simpleapi.api.hub.serverdisplay.ServerDisplayManager;
import com.exorath.simpleapi.api.hub.serverlist.*;
import com.exorath.simpleapi.api.lib.VectorSerializer;
import com.exorath.simpleapi.impl.SimpleAPIImpl;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Toon Sevrin on 5/29/2016.
 */
public class ServerDisplayManagerImpl implements ServerDisplayManager, Listener {
    private Hub hub;
    private HashMap<String, GameServerFilter> filters = new HashMap<>();
    private Set<ServerDisplay> displays = new HashSet<>();

    public ServerDisplayManagerImpl(Hub hub) {
        this.hub = hub;
        Bukkit.getPluginManager().registerEvents(this, SimpleAPIImpl.getInstance());
        loadServers();
        loadSigns(hub.getWorldConfiguration());
    }

    public void loadServers() {
        ServerListManager serverListManager = SimpleAPI.getInstance().getManager(ServerListManager.class);
        if (serverListManager != null)
            for (GameServer server : serverListManager.getGameServers())
                registerGameServer(server);
    }

    public void loadSigns(YamlConfiguration worldConfiguration) {
        if (worldConfiguration.isConfigurationSection("signs")) {
            ConfigurationSection signs = worldConfiguration.getConfigurationSection("signs");
            for (String signKey : signs.getKeys(false))
                if (signs.isConfigurationSection(signKey))
                    loadSign(signs.getConfigurationSection(signKey));
        }
    }

    private void loadSign(ConfigurationSection signSection) {
        if (signSection.contains("location")) {
            if (signSection.contains("type") && signSection.contains("filter"))
                addDisplay(new SignDisplay(getLocation(signSection.getString("location")),
                        signSection.getString("filter"),
                        signSection.getString("type")));
            else
                addDisplay(new SignDisplay(getLocation(signSection.getString("location"))));
        }
    }

    private Location getLocation(String vectorText) {
        return VectorSerializer.deserializeLocation(hub.getHubWorld(), vectorText);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerAdd(GameServerAddedEvent event) {
        if (!event.isCancelled())
            registerGameServer(event.getGameServer());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerRemove(GameServerRemovedEvent event) {
        if (!event.isCancelled())
            deregisterGameServer(event.getGameServer());
    }

    /**
     * Handle sign clicks.
     */
    @EventHandler
    public void onSignClick(PlayerInteractEvent e){
        if (e.getClickedBlock() != null && e.getClickedBlock().getState() instanceof Sign)
            for(ServerDisplay display : displays)
                if(display instanceof SignDisplay)
                    ((SignDisplay) display).clicked(SimpleAPI.getInstance().getGamePlayer(e.getPlayer()), e.getAction());
    }

    public void registerGameServer(GameServer server) {
        for (ServerDisplay display : displays) {
            if (display.getFilterId() == null || (filters.containsKey(display.getFilterId()) && filters.get(display.getFilterId()).allowed(server)))
                display.register(server);
            else
                display.register(server);
        }
    }

    public void deregisterGameServer(GameServer server) {
        for (ServerDisplay display : displays)
            display.deregister(server);
    }

    @Override
    public void addFilter(String id, GameServerFilter filter) {
        filters.put(id, filter);
    }

    @Override
    public HashMap<String, GameServerFilter> getFilters() {
        return filters;
    }

    @Override
    public void addDisplay(ServerDisplay display) {
        displays.add(display);
    }

    @Override
    public void removeDisplay(ServerDisplay display) {
        displays.remove(display);
    }
}
