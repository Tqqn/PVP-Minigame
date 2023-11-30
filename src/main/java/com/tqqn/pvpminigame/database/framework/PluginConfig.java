package com.tqqn.pvpminigame.database.framework;

import com.tqqn.pvpminigame.database.DatabaseModule;
import com.tqqn.pvpminigame.database.models.GamePlayer;
import com.tqqn.pvpminigame.database.models.PlayerStats;
import org.bukkit.configuration.file.FileConfiguration;

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

    public void addNewPlayerTemplate(UUID uuid, String name) {
        playerConfig.createSection(uuid.toString());
        saveValueToConfig(getPlayerPath(uuid) + ".uuid", uuid.toString());
        saveValueToConfig(getPlayerPath(uuid) + ".name", name);
        saveValueToConfig(getPlayerPath(uuid) + ".1v1.kills", 0);
        saveValueToConfig(getPlayerPath(uuid) + ".1v1.deaths", 0);
        saveValueToConfig(getPlayerPath(uuid) + ".1v1.wins", 0);
        saveValueToConfig(getPlayerPath(uuid) + ".2v2.kills", 0);
        saveValueToConfig(getPlayerPath(uuid) + ".2v2.deaths", 0);
        saveValueToConfig(getPlayerPath(uuid) + ".buildffa.kills", 0);
        saveValueToConfig(getPlayerPath(uuid) + ".buildffa.deaths", 0);
        saveValueToConfig(getPlayerPath(uuid) + ".buildffa.highest-killstreak", 0);
        databaseModule.savePlayerConfig(playerConfig);
    }

    public boolean doesGamePlayerExist(UUID uuid) {
        return playerConfig.contains(uuid.toString());
    }

    public GamePlayer getGamePlayerFromConfig(UUID uuid) {
        System.out.println("debug1");
        return new GamePlayer(
                UUID.fromString(playerConfig.getString(uuid + ".uuid")),
                playerConfig.getString(getPlayerPath(uuid) + ".name"), getPlayerStatsFromConfig(uuid));
    }

    public void saveGamePlayerToConfig(GamePlayer gamePlayer) {
        playerConfig.set(getPlayerPath(gamePlayer.getUUID()), gamePlayer.getUUID());
        playerConfig.set(getPlayerPath(gamePlayer.getUUID()) + ".name", gamePlayer.getName());
        databaseModule.savePlayerConfig(playerConfig);
    }

    public PlayerStats getPlayerStatsFromConfig(UUID uuid) {
        return new PlayerStats(
                playerConfig.getInt(getPlayerPath(uuid) + ".1v1.kills"),
                playerConfig.getInt(getPlayerPath(uuid) + ".1v1.deaths"),
                playerConfig.getInt(getPlayerPath(uuid) + ".1v1.wins"),
                playerConfig.getInt(getPlayerPath(uuid) + ".2v2.kills"),
                playerConfig.getInt(getPlayerPath(uuid) + ".2v2.deaths"),
                playerConfig.getInt(getPlayerPath(uuid) + ".2v2.wins"),
                playerConfig.getInt(getPlayerPath(uuid) + ".buildffa.kills"),
                playerConfig.getInt(getPlayerPath(uuid) + ".buildffa.deaths"),
                playerConfig.getInt(getPlayerPath(uuid) + ".buildffa.highest-killstreak")
        );
    }

    public void savePlayerStats(GamePlayer gamePlayer) {
        playerConfig.set(getPlayerPath(gamePlayer.getUUID()), gamePlayer.getUUID());
        saveValueToConfig(gamePlayer.getUUID() + ".1v1.kills", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.ONE_V_ONE_KILLS));
        saveValueToConfig(gamePlayer.getUUID() + ".1v1.deaths", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.ONE_V_ONE_DEATHS));
        saveValueToConfig(gamePlayer.getUUID() + ".1v1.wins", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.ONE_V_ONE_WINS));
        saveValueToConfig(gamePlayer.getUUID() + ".2v2.kills", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.TWO_V_TWO_KILLS));
        saveValueToConfig(gamePlayer.getUUID() + ".2v2.deaths", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.TWO_V_TWO_DEATHS));
        saveValueToConfig(gamePlayer.getUUID() + ".2v2.wins", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.TWO_V_TWO_WINS));
        saveValueToConfig(gamePlayer.getUUID() + ".buildffa.kills", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.BUILDFFA_KILLS));
        saveValueToConfig(gamePlayer.getUUID() + ".buildffa.deaths", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.BUILDFFA_DEATHS));
        saveValueToConfig(gamePlayer.getUUID() + ".buildffa.highest-killstreak", gamePlayer.getPlayerStats().getStat(PlayerStats.StatType.BUILDFFA_HIGHEST_STREAK));

        databaseModule.savePlayerConfig(playerConfig);
    }

    public String getPlayerPath(UUID uuid) {
        return uuid + ".uuid";
    }

    public String getGame() {
        return defaultConfig.getString("game");
    }

    public void saveValueToConfig(String path, Object value) {
        playerConfig.set(path, value);
    }
}
