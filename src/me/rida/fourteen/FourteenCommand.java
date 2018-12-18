package me.rida.fourteen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import me.rida.fourteen.Fourteen;

public class FourteenCommand implements CommandExecutor {
	private Fourteen Fourteen;
	protected FourteenCommand(Fourteen Fourteen) {
		this.Fourteen = Fourteen;
	}

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
						String countAsString = Long.toString(count+1);
						String spam = Fourteen.getConfig().getString("spam");
						Bukkit.getOnlinePlayers().forEach(p -> p.chat(ChatColor.translateAlternateColorCodes('&', spam.replaceAll("%counter%", countAsString).replaceAll("%sender%", s.getName()).replaceAll("%player%", p.getName()))));
					}
					s.sendMessage(ChatColor.translateAlternateColorCodes('&', Fourteen.getConfig().getString("done")));
				}
			}
		}.runTaskAsynchronously(Fourteen);
		return true;
	}
}