package com.github.mcnagatuki.kickcraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CommandManager implements CommandExecutor, TabCompleter {
    private final String prefixAccept = ChatColor.GREEN + "[KickCraft]" + ChatColor.RESET + " ";
    private final String prefixReject = ChatColor.RED + "[KickCraft]" + ChatColor.RESET + " ";

    private boolean same(String a, String b) {
        return a.equals(b);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0)
            return false;


        // start
        if (same(args[0], "start")) {
            KickCraft.plugin.start();
            sender.sendMessage(prefixAccept + " Plugin is started.");
            return true;
        }

        // stop
        if (same(args[0], "stop")) {
            KickCraft.plugin.stop();
            sender.sendMessage(prefixAccept + " Plugin is stopped.");
            return true;
        }

        sender.sendMessage(prefixReject + " Invalid arguments.");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return Stream.of("start", "stop")
                    .filter(e -> e.startsWith(args[0]))
                    .collect(Collectors.toList());
        }
        return null;
    }
}