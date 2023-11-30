package com.tqqn.pvpminigame.games;

import com.tqqn.pvpminigame.PVPMinigame;
import com.tqqn.pvpminigame.utils.GameTypes;
import com.tqqn.pvpminigame.utils.AbstractGame;

public class GameOneVOne extends AbstractGame {

    private final PVPMinigame plugin;

    public GameOneVOne(PVPMinigame plugin) {
        super(plugin, GameTypes.ONE_V_ONE);
        this.plugin = plugin;
    }
    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}
