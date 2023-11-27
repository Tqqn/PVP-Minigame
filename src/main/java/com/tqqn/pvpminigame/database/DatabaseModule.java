package com.tqqn.pvpminigame.database;

import com.tqqn.pvpminigame.PVPMinigame;
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

    public DatabaseModule(PVPMinigame plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onEnable() {
        plugin.saveDefaultConfig();
        createCustomConfig();
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
        playerConfigFile = new File(plugin.getDataFolder(), "custom.yml");
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

    public static void savePlayer(GamePlayer gamePlayer) {
        CompletableFuture.runAsync(gamePlayer::save);
    }

    public void loadGamePlayer(UUID uuid, Player player) {
        CompletableFuture.runAsync()
        GamePlayer.addToCache(uuid, new GamePlayer(uuid, player.getName()));
    }

    public void unLoadGamePlayer(Player player) {
        savePlayer(GamePlayer.from(player));
        GamePlayer.removeFromCache(player);
    }

}
