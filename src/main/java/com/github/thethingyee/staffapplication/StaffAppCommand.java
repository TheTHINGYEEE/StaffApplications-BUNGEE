package com.github.thethingyee.staffapplication;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class StaffAppCommand extends Command {

    public final StaffApplication staffApplication;

    public StaffAppCommand(StaffApplication staffApplication) {
        super("staffapplication", "staffapplication.admin", "staffapp", "sa");
        this.staffApplication = staffApplication;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if(args.length > 1) {
                if(args[0].equalsIgnoreCase("setmessage")) {
                    StringBuilder stringBuilder = new StringBuilder();

                    for(int i = 1; i < args.length; i++) {
                        stringBuilder.append(args[i] + " ");
                    }
                    String message = stringBuilder.toString();
                    Configuration configuration = staffApplication.configuration;
                    configuration.set("staffapplication.message", message);
                    staffApplication.saveConfig();
                    player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.GREEN + "Successfully setupped the message!"));
                } else {
                    player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.RED + "Argument not found. Available commands for more than 1 argument: setmessage"));
                }
            }
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("open")) {
                    Configuration configuration = staffApplication.configuration;
                    if(!configuration.getBoolean("staffapplication.opened")) {
                        configuration.set("staffapplication.opened", true);
                        staffApplication.saveConfig();
                        player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.GREEN + "Staff Applications are now opened!"));
                    } else {
                        player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.RED + "Staff Applications is already opened."));
                    }
                } else if(args[0].equalsIgnoreCase("close")) {
                    Configuration configuration = staffApplication.configuration;
                    if(configuration.getBoolean("staffapplication.opened")) {
                        configuration.set("staffapplication.opened", false);
                        staffApplication.saveConfig();
                        player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.RED + "Staff Applications are now closed."));
                    } else {
                        player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.RED + "Staff Applications is already closed."));
                    }
                } else if(args[0].equalsIgnoreCase("status")) {
                    Configuration configuration = staffApplication.configuration;
                    player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.GRAY + "Message: " + ChatColor.translateAlternateColorCodes('&', configuration.getString(("staffapplication.message")))));
                    if(configuration.getBoolean("staffapplication.opened")) {
                        player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.GRAY + "Opened: " + ChatColor.GREEN + "TRUE"));
                    } else {
                        player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.GRAY + "Opened: " + ChatColor.RED + "FALSE"));
                    }
                } else if(args[0].equalsIgnoreCase("apply")) {
                    player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.RED + "Please use the /apply command since this version doesn't support cross-commands yet."));
                } else {
                    player.sendMessage(new TextComponent(staffApplication.prefix + ChatColor.RED + "Argument not found. Available commands for 1 argument: open, close, status"));
                }
            }
            if(args.length == 0) {
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&6&lStaffApplication Plugin &7ver. 1.0")));
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7Plugin made by: &aThingYeeDEV")));
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "&7Version: &a1.0-BUNGEE")));
            }
        }
    }
}
