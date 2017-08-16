package me.andrewboy159.spawnerdrops;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;

import me.andrewboy159.spawnerdrops.events.EventsClass;

public class SpawnerDrops extends JavaPlugin {
	PluginDescriptionFile pdf = getDescription();
	public CommentedConfiguration config;

	@Override
	public void onEnable() {
	    try {

        } catch(UnknownDependencyException exception) {
	        exception.printStackTrace();
	        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Looks like you forgot to download SilkSpawners!");
	        return;
        }
		loadConfig();
		getServer().getPluginManager().registerEvents(new EventsClass(), this);
		getCommand("spawnerdrops").setExecutor(new Commands());
		getServer().getConsoleSender()
				.sendMessage(ChatColor.GREEN + pdf.getName() + " v" + pdf.getVersion() + " has been enabled!");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender()
				.sendMessage(ChatColor.RED + pdf.getName() + " v" + pdf.getVersion() + " has been disabled!");
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void reloadConfigs() {
		this.config.load();
		this.config.save();
		loadConfig();
	}
}
