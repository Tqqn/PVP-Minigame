package com.tqqn.pvpminigame;

import com.tqqn.pvpminigame.database.DatabaseModule;
import com.tqqn.pvpminigame.utils.AbstractGame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PVPMinigame extends JavaPlugin {

    private DatabaseModule databaseModule;
    private AbstractGame abstractGame;

    @Override
    public void onEnable() {
        databaseModule = new DatabaseModule(this);
        databaseModule.onEnable();

        //findGame();
        //abstractGame.onEnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void findGame() {
        try {
            Class<?> gameModuleClass = Class.forName("com.tqqn.pvpminigame.games." + databaseModule.getPluginConfig().getGame());
            Bukkit.getLogger().info("Using game: " + databaseModule.getPluginConfig().getGame());
            abstractGame = (AbstractGame) gameModuleClass.getConstructors()[0].newInstance(this);
        } catch (Exception ignored) {
            Bukkit.getLogger().info("Unknown Game! Disabling Server.");
            Bukkit.getServer().shutdown();
        }
    }

    public DatabaseModule getDatabaseModule() {
        return databaseModule;
    }
}
