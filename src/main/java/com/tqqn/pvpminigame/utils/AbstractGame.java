package com.tqqn.pvpminigame.utils;

import com.tqqn.pvpminigame.PVPMinigame;

public abstract class AbstractGame extends AbstractModule {

    private final GameTypes gameTypes;

    public AbstractGame(PVPMinigame plugin, GameTypes gameTypes) {
        super(plugin);
        this.gameTypes = gameTypes;
    }

    public GameTypes getGameTypes() {
        return gameTypes;
    }
}
