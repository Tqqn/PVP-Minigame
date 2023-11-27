package com.tqqn.pvpminigame.database.framework;

import com.tqqn.pvpminigame.database.DatabaseModule;
import com.tqqn.pvpminigame.database.models.GamePlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.UUID;

public class PluginConfig {

    private final DatabaseModule databaseModule;
    private final FileConfiguration defaultConfig;
    private final FileConfiguration playerConfig;

    public PluginConfig(DatabaseModule databaseModule) {
        this.databaseModule = databaseModule;
        this.defaultConfig = databaseModule.getDefaultConfig();
        this.playerConfig = databaseModule.getPlayerConfig();
    }

    public GamePlayer getGamePlayerFromConfig(UUID uuid) {
        return new GamePlayer(
                UUID.fromString(playerConfig.getString(uuid + ".uuid")),
                playerConfig.getString(uuid + ".name"));
    }

    public void saveGamePlayerToConfig(GamePlayer gamePlayer) {
        if (playerConfig.getConfigurationSection(gamePlayer.getUUID().toString()) == null) {
            playerConfig.createSection(gamePlayer.getUUID().toString());
        }
        playerConfig.set(gamePlayer.getUUID() + ".uuid", gamePlayer.getUUID());
        playerConfig.set(gamePlayer.getUUID() + ".name", gamePlayer.getName());
        databaseModule.savePlayerConfig(playerConfig);
    }
}
