package com.marlongrazek.bukkitutils;

import org.bukkit.entity.Player;

public final class BukkitUtils {

    public static void setPlayerExperience(Player player, int experience) {

        player.setExp(0);
        player.setLevel(0);

        addPlayerExperience(player, experience);
    }

    public static void addPlayerExperience(Player player, int experience) {

        while (experience >= player.getExpToLevel() - player.getExpToLevel() * player.getExp()) {
            experience -= player.getExpToLevel() - player.getExpToLevel() * player.getExp();
            player.setLevel(player.getLevel() + 1);
            player.setExp(0);
        }

        float newExp = experience + player.getExp() * player.getExpToLevel();
        player.setExp(newExp / player.getExpToLevel());
    }

    public static void removePlayerExperience(Player player, int experience) {

        if (player.getExp() == 0) return;

        while (experience > player.getExpToLevel() * player.getExp()) {
            experience -= player.getExpToLevel() * player.getExp();
            if (player.getLevel() > 0) player.setLevel(player.getLevel() - 1);
        }

        float newExp = player.getExpToLevel() * player.getExp() - experience;
        player.setExp(newExp / player.getExpToLevel());
    }
}
