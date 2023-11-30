package com.tqqn.pvpminigame.database;

import com.tqqn.pvpminigame.PVPMinigame;
import com.tqqn.pvpminigame.database.framework.PluginConfig;
import com.tqqn.pvpminigame.database.listeners.PlayerJoinListener;
import com.tqqn.pvpminigame.database.listeners.PlayerQuitListener;
import com.tqqn.pvpminigame.database.models.GamePlayer;
import com.tqqn.pvpminigame.utils.AbstractModule;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class DatabaseModule extends AbstractModule {

    private final PVPMinigame plugin;
    private final ArrayList<BukkitRunnable> runnables = new ArrayList<>();
    private File playerConfigFile;
    private FileConfiguration playerConfig;
    private static PluginConfig pluginConfig;

    public DatabaseModule(PVPMinigame plugin) {
        super(plugin);
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {
        plugin.saveDefaultConfig();
        createCustomConfig();

        pluginConfig = new PluginConfig(this);

        registerEvent(new PlayerJoinListener(this));
        registerEvent(new PlayerQuitListener());
    }

    @Override
    public void onDisable() {
        for (BukkitRunnable runnable : runnables) {
            runnable.cancel();
            runnables.remove(runnable);
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            unLoadGamePlayer(player);
        }
    }

    private void createCustomConfig() {
        playerConfigFile = new File(plugin.getDataFolder(), "players.yml");
        if (!playerConfigFile.exists()) {
            playerConfigFile.getParentFile().mkdirs();
            plugin.saveResource("players.yml", false);
        }

        playerConfig = new YamlConfiguration();

        try {
            playerConfig.load(playerConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getDefaultConfig() {
        return plugin.getConfig();
    }

    public FileConfiguration getPlayerConfig() {
        return playerConfig;
    }

    public void savePlayerConfig(FileConfiguration fileConfiguration) {
        try {
            fileConfiguration.save(playerConfigFile);
        } catch (IOException ignored) { }
    }

    public static CompletableFuture savePlayer(GamePlayer gamePlayer) {
        return CompletableFuture.runAsync(() -> pluginConfig.saveGamePlayerToConfig(gamePlayer));
    }

    public void loadGamePlayer(UUID uuid) {
        GamePlayer.addToCache(uuid, pluginConfig.getGamePlayerFromConfig(uuid));
    }

    public void unLoadGamePlayer(Player player) {
        savePlayer(GamePlayer.from(player));
        GamePlayer.removeFromCache(player);
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }
}
