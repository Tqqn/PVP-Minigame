package com.tqqn.pvpminigame.database.listeners;

import com.tqqn.pvpminigame.database.DatabaseModule;
import com.tqqn.pvpminigame.database.events.GamePlayerQuitEvent;
import com.tqqn.pvpminigame.database.models.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        DatabaseModule.savePlayer(GamePlayer.from(event.getPlayer())).thenRun(() -> {
            Bukkit.getPluginManager().callEvent(new GamePlayerQuitEvent(GamePlayer.from(event.getPlayer())));
        });
    }
}
