package me.rida.fourteen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import me.rida.fourteen.Fourteen;

public class FourteenCommand implements CommandExecutor {
	private final Fourteen Fourteen;
	protected FourteenCommand(Fourteen Fourteen) {
		this.Fourteen = Fourteen;
	}
	private final List<String> colors = Arrays.asList("&a", "&b", "&c", "&d", "&e", "&f", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&0");
	@Override
	public boolean onCommand(CommandSender s, Command c, String a, String[] g) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!s.hasPermission(Fourteen.getConfig().getString("permission"))) {
					s.sendMessage(ChatColor.translateAlternateColorCodes('&', Fourteen.getConfig().getString("noPerm")));
				}
				else if (g.length == 1) {
					if (g[0].equalsIgnoreCase("reload")) {
						Fourteen.reloadConfig();
						Fourteen.saveConfig();
						s.sendMessage(ChatColor.translateAlternateColorCodes('&', Fourteen.getConfig().getString("reload")));
					}
					else {
						s.sendMessage(ChatColor.translateAlternateColorCodes('&', Fourteen.getConfig().getString("unknown")));
					}
				}
				else if (g.length == 0) {
					long count;
					for (count = Fourteen.getConfig().getLong("count") - 1; count >=0; --count) {
						final String countAsString = Long.toString(count+1);
						final String spam = Fourteen.getConfig().getString("spam");
						String color = (String) colors.get(new Random().nextInt(colors.size()));
						if (Fourteen.getConfig().getBoolean("rainbow") != true) {
							color = "";
						}
						final String color2 = color;
						Bukkit.getOnlinePlayers().forEach(p -> p.chat(ChatColor.translateAlternateColorCodes('&', color2 + spam.replaceAll("%counter%", countAsString).replaceAll("%sender%", s.getName()).replaceAll("%player%", p.getName()))));
					}
					s.sendMessage(ChatColor.translateAlternateColorCodes('&', Fourteen.getConfig().getString("done")));
				}
			}
		}.runTaskLaterAsynchronously(Fourteen, (long) (Fourteen.getConfig().getDouble("delay") * 20));
		return true;
	}
}