package com.tqqn.pvpminigame.database.events;

import com.tqqn.pvpminigame.database.models.GamePlayer;
import org.bukkit.event.HandlerList;

public class GamePlayerQuitEvent extends GamePlayerEvent {

    private static final HandlerList handlers = new HandlerList();

    public GamePlayerQuitEvent(final GamePlayer gamePlayer) {
        super(gamePlayer);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
