package me.andrewboy159.spawnerdrops.events;

import me.andrewboy159.spawnerdrops.SpawnerDrops;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class EventsClass implements Listener {
	private Plugin plugin = SpawnerDrops.getPlugin(SpawnerDrops.class);

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {

		int number = (int) Math.ceil(Math.random() * plugin.getConfig().getInt("percentage"));
		if (!event.getEntityType().toString().equalsIgnoreCase("player")
				&& event.getEntity().getKiller().getType().toString().equalsIgnoreCase("player")) {
			if (number == 1) {
				Player player = event.getEntity().getKiller();
				try {
					if (player.hasPermission("spawnerdrops.collect")) {

						plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(),
								"ss give " + player.getName() + " " + event.getEntityType().toString().toLowerCase());
						player.sendMessage(ChatColor.AQUA + "You got lucky and received a " + ChatColor.GREEN
								+ event.getEntityType().toString().toLowerCase() + ChatColor.GREEN + " Spawner!");
						player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2F, 1F);
					}
				} catch (Exception e) {
					plugin.getServer().getConsoleSender().sendMessage(
							ChatColor.RED + "[SpawnerDrops] An error occured! Do you have SilkSpawners installed?");
				}
			}
		}
	}
}
