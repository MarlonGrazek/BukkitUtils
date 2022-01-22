package com.marlongrazek.bukkitutils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class BukkitUtils {

    public static void setPlayerExperience(Player player, int experience) {

        player.setExp(0);
        player.setLevel(0);

        addPlayerExperience(player, experience);
    }

    public static void addPlayerExperience(Player player, int experience) {

        while (experience == player.getExpToLevel() - (int) (player.getExpToLevel() * player.getExp())) {
            experience -= player.getExpToLevel() - (int) (player.getExpToLevel() * player.getExp());
            player.setLevel(player.getLevel() + 1);
        }

        Bukkit.broadcastMessage("ToLevel:" + player.getExpToLevel());
        Bukkit.broadcastMessage("Left:" + (player.getExpToLevel() - (int) (player.getExpToLevel() * player.getExp())));
        Bukkit.broadcastMessage("Progress:" + (int) (player.getExpToLevel() * player.getExp()));
        Bukkit.broadcastMessage("NewProgress:" + (float) experience / player.getExpToLevel());

        player.setExp((float) experience / player.getExpToLevel());
    }

    public static void removePlayerExperience(Player player, int experience) {

        if (player.getExp() == 0) return;

        while (experience > (int) (player.getExpToLevel() * player.getExp())) {
            experience -= (int) (player.getExpToLevel() * player.getExp());
            if (player.getLevel() > 0) player.setLevel(player.getLevel() - 1);
        }

        int newExp = (int) (player.getExpToLevel() * player.getExp()) - experience;
        player.setExp((float) newExp / player.getExpToLevel());
    }
}
