package com.tqqn.pvpminigame.database.listeners;

import com.tqqn.pvpminigame.database.DatabaseModule;
import com.tqqn.pvpminigame.database.events.GamePlayerJoinEvent;
import com.tqqn.pvpminigame.database.models.GamePlayer;
import com.tqqn.pvpminigame.nms.v1_20_R2.v1_20_R2;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerJoinListener implements Listener {

    private final DatabaseModule databaseModule;

    public PlayerJoinListener(DatabaseModule databaseModule) {
        this.databaseModule = databaseModule;
    }

    @EventHandler
    public void onAsyncLogin(AsyncPlayerPreLoginEvent event) {
        if (!databaseModule.getPluginConfig().doesGamePlayerExist(event.getUniqueId())) {
            databaseModule.getPluginConfig().addNewPlayerTemplate(event.getUniqueId(), event.getName());
        }
        databaseModule.loadGamePlayer(event.getUniqueId());
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (GamePlayer.from(event.getPlayer()) == null) {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage("You do not exist.");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        GamePlayerJoinEvent gamePlayerJoinEvent = new GamePlayerJoinEvent(GamePlayer.from(event.getPlayer()));
        Bukkit.getPluginManager().callEvent(gamePlayerJoinEvent);
        v1_20_R2 test = new v1_20_R2();
        test.createTeam(event.getPlayer());
    }
}
