package com.github.mcnagatuki.kickcraft;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class KickCraft extends JavaPlugin implements Listener {
    public static KickCraft plugin;
    private boolean isRunning = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        this.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("kickcraft").setExecutor(new CommandManager());
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!isRunning) return;

        Entity damagedEntity = event.getEntity();
        Entity damagerEntity = event.getDamager();

        if (!(damagedEntity instanceof Player)) return;
        if (!(damagerEntity instanceof Player)) return;

        Player damagedPlayer = (Player) damagedEntity;
        Player damagerPlayer = (Player) damagerEntity;

        String message = String.format(
                "%sは%sにキックされたのでキックされました",
                ChatColor.GOLD + damagedPlayer.getName() + ChatColor.RESET,
                ChatColor.DARK_GREEN + damagerPlayer.getName() + ChatColor.RESET
                );
        getServer().broadcastMessage(message);

        damagedPlayer.kickPlayer("キックされたためキックされました");
    }


    public void start() {
        if (isRunning) return;
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }
}
