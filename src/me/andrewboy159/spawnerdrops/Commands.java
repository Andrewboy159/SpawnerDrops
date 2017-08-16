package me.andrewboy159.spawnerdrops;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Commands implements CommandExecutor {

	private Plugin plugin = SpawnerDrops.getPlugin(SpawnerDrops.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sds") || cmd.getName().equalsIgnoreCase("spawnerdrops")) {
			if (args.length == 0 || (args.length > 0 && args[0].equalsIgnoreCase("help"))) {
				sender.sendMessage(ChatColor.AQUA + "\nCommands for SpawnerDrops:\n\n" + ChatColor.GREEN
						+ "=====================");
				sender.sendMessage(
						ChatColor.GREEN + "\n/sds help\n" + ChatColor.AQUA + "Displays this help message.\n");
				sender.sendMessage(ChatColor.GREEN + "\n/sds percent [value]\n" + ChatColor.AQUA
						+ "Displays or changes the \"percent\" value in the config.\n");
				sender.sendMessage(ChatColor.GREEN + "\n/sds reload\n" + ChatColor.AQUA + "Reloads the plugin.\n");
				return true;
			} else if ((args.length >= 1 && (args[0].equalsIgnoreCase("percent") || args[0].equalsIgnoreCase("rl")))
					&& (sender.hasPermission("spawnerdrops.percent.view")
							|| sender.hasPermission("spawnerdrops.percent"))) {
				if (args.length == 1) {
					int percent = plugin.getConfig().getInt("percentage");

					sender.sendMessage(ChatColor.AQUA + "[SpawnerDrops] The percentage defined in the config is: "
							+ ChatColor.GREEN + percent);
					return true;
				} else if (args.length == 2) {
				    int percent = Integer.parseInt(args[1]);
					try {
						percent = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
						sender.sendMessage(ChatColor.RED + "That is not a valid number1");
					}
					try {
						plugin.getConfig().set("percentage", percent);
						plugin.saveConfig();
						plugin.reloadConfig();

						sender.sendMessage(ChatColor.AQUA
								+ "[SpawnerDrops] You have successfully changed the percentage in the config to: "
								+ ChatColor.GREEN + percent);
						return true;
					} catch (Exception e) {
						sender.sendMessage(ChatColor.RED + "[SpawnerDrops] You have entered an invalid value!");
						return false;
					}
				} else {
					sender.sendMessage(ChatColor.RED + "[SpawnerDrops] Incorrect number of arguments!");
					return false;
				}
			} else if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("spawnerdrops.reload")) {
				sender.sendMessage(ChatColor.RED + "[SpawnerDrops] Reloading plugin...");
				plugin.reloadConfig();
				plugin.getServer().getPluginManager().disablePlugin(plugin);
				plugin.getServer().getPluginManager().enablePlugin(plugin);
				sender.sendMessage(ChatColor.AQUA + "[SpawnerDrops] Successfully reloaded the plugin!");
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "[SpawnerDrops] Undefined command!");
				return false;
			}
		}
		return false;
	}
}
