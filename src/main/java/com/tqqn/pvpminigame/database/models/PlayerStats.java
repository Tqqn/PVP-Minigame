package com.tqqn.pvpminigame.database.models;

import java.util.HashMap;

public class PlayerStats {

    private final HashMap<StatType, Integer> stats;

    public PlayerStats(int killsOneVOne, int deathsOneVOne, int winsOneVOne, int killsTwoVTwo, int deathsTwoVTwo, int winsTwoVTwo, int killsBuildFFA, int deathsBuildFFA, int streakBuildFFA) {
        stats = new HashMap<>();
        stats.put(StatType.ONE_V_ONE_KILLS, killsOneVOne);
        stats.put(StatType.ONE_V_ONE_DEATHS, deathsOneVOne);
        stats.put(StatType.ONE_V_ONE_WINS, winsOneVOne);
        stats.put(StatType.TWO_V_TWO_KILLS, killsTwoVTwo);
        stats.put(StatType.TWO_V_TWO_DEATHS, deathsTwoVTwo);
        stats.put(StatType.TWO_V_TWO_WINS, winsTwoVTwo);
        stats.put(StatType.BUILDFFA_KILLS, killsBuildFFA);
        stats.put(StatType.BUILDFFA_DEATHS, deathsBuildFFA);
        stats.put(StatType.BUILDFFA_HIGHEST_STREAK, streakBuildFFA);
    }

    public int getStat(StatType statType) {
        return stats.get(statType);
    }

    public void increaseStat(StatType statType) {
        stats.put(statType, stats.get(statType)+1);
    }


    public enum StatType {
        ONE_V_ONE_KILLS,
        ONE_V_ONE_DEATHS,
        ONE_V_ONE_WINS,
        TWO_V_TWO_KILLS,
        TWO_V_TWO_DEATHS,
        TWO_V_TWO_WINS,
        BUILDFFA_KILLS,
        BUILDFFA_DEATHS,
        BUILDFFA_HIGHEST_STREAK;

    }
}
