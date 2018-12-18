package me.rida.fourteen;

import org.bukkit.plugin.java.JavaPlugin;

public class Fourteen extends JavaPlugin {

	@Override
	public void onEnable() {
		this.getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		this.getCommand("14").setExecutor(new FourteenCommand(this));
	}

	@Override
	public void onDisable() {
	}
}