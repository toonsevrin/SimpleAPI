package com.exorath.simpleapi.api.player;

import com.exorath.simpleapi.api.properties.Properties;
import com.exorath.simpleapi.api.database.DBData;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Represents a Bukkit player, connected or not.
 * Created by Toon Sevrin on 5/15/2016.
 */
public interface GamePlayer {

    /**
     * Get the official Minecraft {@link UUID} of this {@link GamePlayer} instance.
     *
     * @return player's UUID
     */
    UUID getUUID();

    /**
     * Gets the Bukkit {@link Player} used to create this {@link GamePlayer} instance. Note that this {@link Player} could be offline.
     *
     * @return this {@link GamePlayer}'s Bukkit {@link Player}
     */
    Player bukkit();

    /**
     * Gets this {@link GamePlayer}'s {@link DBData} specific to this game type, null if there's no game registered.
     * This {@link DBData} is loaded on {@link org.bukkit.event.player.AsyncPlayerPreLoginEvent} meaning it can be accessed synchronously after this event is called ({@link org.bukkit.event.player.PlayerJoinEvent} is safe to use).
     * The data is stored in a database making it persistent over reboots, reloads and different servers.
     *
     * @return this {@link GamePlayer}'s {@link DBData} specific to this game type  or null if no game registered
     */
    DBData getGameData();

    /**
     * Gets the {@link GamePlayer}'s universal DBData. Any Game type can access this data. It is used for GameAPI data and Modules should store data here.
     * This {@link DBData} is loaded on {@link org.bukkit.event.player.AsyncPlayerPreLoginEvent} meaning it can be accessed synchronously after this event is called ({@link org.bukkit.event.player.PlayerJoinEvent} is safe to use).
     * The data is stored in a database making it persistent over reboots, reloads and different servers.
     *
     * @return this {@link GamePlayer}'s {@link DBData} specific to this game type
     */
    DBData getGlobalData();

    /**
     * Gets the properties of this {@link GamePlayer}. This is a runtime key value store (Meaning it is NOT persistent over reloads, rejoins, servers).
     * Use this for easy access to temporary {@link GamePlayer} variables.
     * @return this {@link GamePlayer} properties map.
     */
    Properties getProperties();


}
