package com.github.thethingyee.staffapplication;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class StaffApplication extends Plugin {

    public Configuration configuration;
    public File file;
    public String prefix = ChatColor.translateAlternateColorCodes('&', "&7[&6&lStaffApplication&7] ");

    @Override
    public void onEnable() {
        try {
            file = new File(getDataFolder(), "config.yml");

            if(!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }

            if(!file.exists()) {
                file.createNewFile();
            }

            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration.set("staffapplication.message", "&6Click on this link! 'config.yml message' to apply for staff!");
        configuration.set("staffapplication.opened", true);
        saveConfig();
        this.getProxy().getPluginManager().registerCommand(this, new ApplyCommand(this));
        this.getProxy().getPluginManager().registerCommand(this, new StaffAppCommand(this));
        this.getLogger().info(prefix + ChatColor.GREEN + "Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        saveConfig();
        this.getLogger().info(prefix + ChatColor.RED + "Plugin successfully disabled.");
    }
    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
