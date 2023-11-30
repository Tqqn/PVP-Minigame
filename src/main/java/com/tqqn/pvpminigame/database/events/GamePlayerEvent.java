package com.tqqn.pvpminigame.database.events;

import com.tqqn.pvpminigame.database.models.GamePlayer;
import org.bukkit.event.Event;

public abstract class GamePlayerEvent extends Event {

    private final GamePlayer gamePlayer;

    protected GamePlayerEvent(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
