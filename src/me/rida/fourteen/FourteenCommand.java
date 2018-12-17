package me.rida.fourteen;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import me.rida.fourteen.Fourteen;

public class FourteenCommand implements CommandExecutor {
	public Fourteen Fourteen;
	public FourteenCommand(Fourteen Fourteen) {
		this.Fourteen = Fourteen;
	}

	@Override
	public boolean onCommand(CommandSender s, Command c, String a, String[] g) {
		if (!s.hasPermission(Fourteen.getConfig().getString("permission"))) {
			s.sendMessage(ChatColor.translateAlternateColorCodes('&', Fourteen.getConfig().getString("noPerm")));
			return true;
		}
		new BukkitRunnable() {
			@Override
			public void run() {
				long count;
				for (count = Fourteen.getConfig().getLong("count") - 1; count >=0; --count) {
					String countAsString = Long.toString(count+1);
					Bukkit.getOnlinePlayers().forEach(p -> p.chat(ChatColor.translateAlternateColorCodes('&', Fourteen.getConfig().getString("spam").replaceAll("%counter%", countAsString).replaceAll("%sender%", s.getName()).replaceAll("%player%", p.getName()))));
				}
				s.sendMessage(ChatColor.translateAlternateColorCodes('&', Fourteen.getConfig().getString("done")));
			}

		}.runTaskAsynchronously(Fourteen);
		return true;
	}
}