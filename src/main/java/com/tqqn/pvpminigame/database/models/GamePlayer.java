package com.tqqn.pvpminigame.database.models;

import com.tqqn.pvpminigame.database.DatabaseModule;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GamePlayer {

    private static final ConcurrentMap<UUID, GamePlayer> CACHE = new ConcurrentHashMap<>();

    private final UUID uuid;
    private final String name;

    public GamePlayer(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public CompletableFuture<Void> save() {

    }

    public static GamePlayer from(Player player) {
        if (player == null) return null;
        return CACHE.get(player.getUniqueId());
    }

    public static void removeFromCache(Player player) {
        CACHE.remove(player.getUniqueId());
    }

    public static void addToCache(UUID uuid, GamePlayer gamePlayer) {
        CACHE.put(uuid, gamePlayer);
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
