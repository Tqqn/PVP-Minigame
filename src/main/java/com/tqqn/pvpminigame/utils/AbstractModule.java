package com.tqqn.pvpminigame.utils;

public abstract class AbstractModule {

    public AbstractModule() {
        onEnable();
    }

    public abstract void onEnable();
    public abstract void onDisable();

}
