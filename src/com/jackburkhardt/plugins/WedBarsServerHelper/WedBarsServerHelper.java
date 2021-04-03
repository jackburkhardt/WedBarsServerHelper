package com.jackburkhardt.plugins.WedBarsServerHelper;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class WedBarsServerHelper extends JavaPlugin {

    List<String> worldNames = new ArrayList<>();

    public void onEnable() {
    }

    public void onDisable() {}

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("maps")) {
            worldNames.clear();
            for (World w : getServer().getWorlds()) {
                worldNames.add(w.getName());
            }
            sender.sendMessage(ChatColor.GRAY + "There are currently " + ChatColor.GREEN + ChatColor.BOLD + worldNames.size() + ChatColor.GRAY + " maps enabled on this server:");
            for (String s : worldNames) {
                sender.sendMessage(ChatColor.GREEN + s);
            }
        }
        if (cmd.getName().equalsIgnoreCase("sendtomap")) {
            if (args.length == 1) {
                getServer().broadcastMessage(ChatColor.GRAY + "Sending all players to map " + ChatColor.GREEN + ChatColor.BOLD + args[0]);
                for (Player p : getServer().getOnlinePlayers()) {
                    getServer().dispatchCommand(getServer().getConsoleSender(), "mvtp " + p.getName() + " " + args[0]);
                    p.setGameMode(GameMode.ADVENTURE);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You need to include the name of a map to send players to.");
            }
        }
        return true;
    }

}
