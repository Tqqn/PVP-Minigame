package com.tqqn.pvpminigame.nms;

import org.bukkit.entity.Player;

public interface ReflectionLayer {

    String getVersionString();
    void sendPacket(Player player, Object packet);
}
