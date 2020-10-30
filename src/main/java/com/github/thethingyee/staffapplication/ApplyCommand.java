package com.github.thethingyee.staffapplication;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class ApplyCommand extends Command {

    public final StaffApplication plugin;

    public ApplyCommand(StaffApplication plugin) {
        super("apply", "staffapplication.apply");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            Configuration configuration = plugin.configuration;

            if(configuration.getBoolean("staffapplication.opened")) {
                String message = ChatColor.translateAlternateColorCodes('&', configuration.getString("staffapplication.message"));
                player.sendMessage(new TextComponent(plugin.prefix + message));
            } else {
                player.sendMessage(plugin.prefix + ChatColor.RED + "Sorry, but staff applications are currently closed.");
            }
        }
    }
}
