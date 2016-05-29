package com.exorath.simpleapi.api.hologram;

import org.bukkit.entity.Player;

/**
 * Created by Toon Sevrin on 5/27/2016.
 */
public interface ViewHandler {
    String onView(Hologram hologram, Player player, String string);
}
