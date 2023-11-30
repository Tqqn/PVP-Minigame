package com.tqqn.pvpminigame.nms.v1_20_R2;

import com.tqqn.pvpminigame.nms.ReflectionLayer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
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

    public void createTeam(Player player) {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.addObjective("activeBoard", ObjectiveCriteria.DUMMY, CraftChatMessage.fromStringOrNull("{\"text\":\"" + "" + "\"}"), ObjectiveCriteria.RenderType.INTEGER);
        Objective objective = scoreboard.getObjective("activeBoard");
        objective.setDisplayName(CraftChatMessage.fromStringOrNull("{\"text\":\"" + "Test" + "\"}"));
        ClientboundSetObjectivePacket addObjective = new ClientboundSetObjectivePacket(objective, 0);

        ClientboundSetDisplayObjectivePacket addObjectiveDisplay = new ClientboundSetDisplayObjectivePacket(DisplaySlot.SIDEBAR, objective);

        PlayerTeam playerTeam = new PlayerTeam(scoreboard, "test");
        scoreboard.addPlayerToTeam(player.getName(), playerTeam);
        ClientboundSetPlayerTeamPacket createTeamPacket = ClientboundSetPlayerTeamPacket.createAddOrModifyPacket(playerTeam, false);
        ClientboundSetPlayerTeamPacket addPlayerToTeamPacket = ClientboundSetPlayerTeamPacket.createPlayerPacket(playerTeam, player.getName(), ClientboundSetPlayerTeamPacket.Action.ADD);

        sendPacket(player, createTeamPacket);
        sendPacket(player, addObjective);
        sendPacket(player, addObjectiveDisplay);
        sendPacket(player, addPlayerToTeamPacket);
        System.out.println("send");
    }
}
