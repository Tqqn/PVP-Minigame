package com.tqqn.pvpminigame.utils;

import com.tqqn.pvpminigame.PVPMinigame;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public abstract class AbstractModule {

    private final PVPMinigame plugin;
    public AbstractModule(PVPMinigame plugin) {
        this.plugin = plugin;
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public void registerEvent(Listener event) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(event, plugin);
    }

}
