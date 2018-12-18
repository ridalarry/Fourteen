package me.rida.fourteen;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class Fourteen extends JavaPlugin {

	@Override
	public void onEnable() {
		File file = new File(getDataFolder(), "config.yml");
		if (!file.exists()) {
			this.getConfig().addDefault("count", 10);
			this.getConfig().addDefault("noPerm", "&cNo permission!");
			this.getConfig().addDefault("done", "&aDone!");
			this.getConfig().addDefault("spam", "14");
			this.getConfig().addDefault("permission", "fourteen.use");
			this.getConfig().addDefault("unknown", "&cUnknown argument!");
			this.getConfig().addDefault("reload", "&aReloaded!");
		}
		else {
			if (this.getConfig().getString("count") == null) {
				this.getConfig().addDefault("count", 10);
			}
			if (this.getConfig().getString("noPerm") == null) {
				this.getConfig().addDefault("noPerm", "&cNo permission!");
			}
			if (this.getConfig().getString("done") == null) {
				this.getConfig().addDefault("done", "&aDone!");
			}
			if (this.getConfig().getString("spam") == null) {
				this.getConfig().addDefault("spam", "14");
			}
			if (this.getConfig().getString("permission") == null) {
				this.getConfig().addDefault("permission", "fourteen.use");
			}
			if (this.getConfig().getString("unknown") == null) {
				this.getConfig().addDefault("unknown", "&cUnknown argument!");
			}
			if (this.getConfig().getString("reload") == null) {
				this.getConfig().addDefault("reload", "&aReloaded!");
			}
		}
		this.getConfig().options().copyDefaults(true);
		saveConfig();
		System.out.println("[Fourteen] has been enabled!");
		this.getCommand("14").setExecutor(new FourteenCommand(this));
	}

	@Override
	public void onDisable() {
		System.out.println("[Fourteen] has been disabled!");
	}
}
