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
import com.exorath.simpleapi.api.hub.serverdisplay.ServerDisplay;
import com.exorath.simpleapi.api.hub.serverlist.GameServer;
import com.exorath.simpleapi.api.player.GamePlayer;
import com.sun.javafx.binding.StringFormatter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.block.Action;

import java.util.HashMap;

/**
 * Created by Toon Sevrin on 5/29/2016.
 */
public class SignDisplay implements ServerDisplay {
    private static final String JOIN_LINE = ChatColor.GREEN + "▞ PLAY ▚";
    private static final String TYPE_LINE = ChatColor.YELLOW + ChatColor.BOLD.toString() + ChatColor.UNDERLINE.toString() + "▚ %s ▞";
    private static final String IN_QUEUE_LINE = ChatColor.YELLOW + "%d In Queue";
    private static final String IN_GAME_LINE = ChatColor.YELLOW + "%d In Game";

    private HashMap<String, GameServer> servers = new HashMap<>();

    private String filterId;
    private Location location;
    private String type;

    public SignDisplay(Location location) {
        this(location, null, null);
    }

    public SignDisplay(Location location, String filterId, String type) {
        this.location = location;
        this.filterId = filterId;
        this.type = type == null ? "Default" : type;
    }

    public void clicked(GamePlayer player, Action action){
        for(GameServer server : servers.values()){
            if(server.isPlayable() && server.isJoinable()) {
                SimpleAPI.connect(player, server.getBungeeName());
                return;
            }
        }
    }

    private boolean isEnabled() {
        return location.getBlock().getType() == Material.WALL_SIGN
                || location.getBlock().getType() == Material.SIGN_POST;
    }

    @Override
    public String getFilterId() {
        return filterId;
    }

    @Override
    public void register(GameServer server) {
        servers.put(server.getBungeeName(), server);
        update();
    }

    @Override
    public void deregister(GameServer server) {
        servers.remove(server.getBungeeName());
        update();
    }

    private void update() {
        if (!isEnabled()) {
            System.out.println("Failed to update sign at " + location.toVector().toString() + ", no sign found.");
            return;
        }
        Sign sign = (Sign) location.getBlock().getState();
        sign.setLine(0, JOIN_LINE);
        sign.setLine(1, StringFormatter.format(TYPE_LINE, type).getValue());
        sign.setLine(2, StringFormatter.format(IN_QUEUE_LINE, getPlayersInQueue()).getValue());
        sign.setLine(3, StringFormatter.format(IN_GAME_LINE, getPlayersInGame()).getValue());
        sign.update(true, false);
    }

    private int getPlayersInQueue() {
        int players = 0;
        for (GameServer server : servers.values())
            if(server.isPlayable())
                players += server.getPlayerAmount();
        return players;
    }

    private int getPlayersInGame() {
        int players = 0;
        for (GameServer server : servers.values())
            if(!server.isPlayable())
                players += server.getPlayerAmount();
        return players;
    }
}
