package com.tqqn.pvpminigame.nms.v1_20_R2;

import com.tqqn.pvpminigame.nms.ReflectionLayer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R2.util.CraftChatMessage;
import org.bukkit.entity.Player;

public class v1_20_R2 implements ReflectionLayer {

    @Override
    public String getVersionString() {
        return null;
    }

    @Override
    public void sendPacket(Player player, Object packetObject) {
        Packet packet = (Packet) packetObject;
        ((CraftPlayer)player).getHandle().connection.send(packet);
    }

    public static void createTeam(Player player) {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.addObjective("activeBoard", ObjectiveCriteria.DUMMY, CraftChatMessage.fromStringOrNull("{\"text\":\"" + "" + "\"}"), ObjectiveCriteria.RenderType.INTEGER);
        Objective objective = scoreboard.getObjective("activeBoard");
        scoreboard.setDisplayObjective(DisplaySlot.SIDEBAR, objective);
        objective.setDisplayName(CraftChatMessage.fromStringOrNull("{\"text\":\"" + "Test" + "\"}"));
        scoreboard.addPlayerToTeam(player.getName(), new PlayerTeam(scoreboard, "activeBoard"));
    }
}
